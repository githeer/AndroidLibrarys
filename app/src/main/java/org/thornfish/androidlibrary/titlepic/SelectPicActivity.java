package org.thornfish.androidlibrary.titlepic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yalantis.ucrop.UCrop;

import org.thornfish.androidlibrary.R;

import java.io.File;
import java.util.ArrayList;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;

/**
 * Created by beibeizhu on 17/3/1.
 */

public class SelectPicActivity extends AppCompatActivity implements TakePhoto.TakeResultListener, InvokeListener {

    private CustomHelper mCustomHelper;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private ArrayList<TImage> images = new ArrayList<>();
    private int index = 0;
    private UCrop.Options options;
    private String url;//图片名称
    private String PIC_NAME = "wawaji";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        takePhoto = getTakePhoto();
        takePhoto.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select);
        mCustomHelper = new CustomHelper();

        options=new UCrop.Options();
        options.setCircleDimmedLayer(true);
        options.setToolbarColor(Color.parseColor("#fd5757"));
        initViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }


    protected void setStatusBar() {
        StatusBarUtil.setColor(this, Color.TRANSPARENT,0);
    }

    protected void initViews() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.select_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        takePhoto.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            index++;
            if (index < images.size()) {
                crop(index);
            } else {
                if(url!=null){
                    data.putExtra("url",url);
                }
                setResult(1, data);
                finish();
//                Toast.makeText(getApplicationContext(), "全部完成"+resultUri, Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            if(url!=null){
                data.putExtra("url",url);
            }
            setResult(2, data);
            finish();
        }
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    public void Click(View view) {
        mCustomHelper.onClick(view, getTakePhoto());
    }
    public void cancel(View view) {
        finish();
    }

    @Override
    public void takeSuccess(TResult result) {
        images = result.getImages();
        index = 0;
        crop(index);
    }


    public void crop(int position) {
        String compressPath = images.get(position).getCompressPath();
        Uri imageContentUri = UriUtils.getImageContentUri(SelectPicActivity.this, new File(compressPath));
        url = PIC_NAME + ".jpg";//System.currentTimeMillis()
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + url);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri destinationUri = Uri.fromFile(file);
        UCrop.of(imageContentUri, destinationUri)
                .withOptions(options)
//                .withAspectRatio(1, 1)
                .withMaxResultSize(maxWidth, maxHeight)
                .start(SelectPicActivity.this);
    }


    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }
}
