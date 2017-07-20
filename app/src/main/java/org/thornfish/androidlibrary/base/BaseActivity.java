package org.thornfish.androidlibrary.base;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import org.thornfish.androidlibrary.R;
import org.thornfish.androidlibrary.application.Application;
import org.thornfish.androidlibrary.utils.NetBroadcastReceiver;
import org.thornfish.androidlibrary.utils.NetUtil;
import org.thornfish.androidlibrary.utils.ToastUtil;


public abstract class BaseActivity extends AutoLayoutActivity implements NetBroadcastReceiver.NetEvevt {
    protected Application mApplication;

    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;


    @SuppressWarnings("static-access")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        PushAgent.getInstance(this).onAppStart();

        evevt = this;
        inspectNet();

        mApplication = (Application) getApplication();
        mApplication.addActivity(this);
    }

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        return isNetConnect();

        // if (netMobile == 1) {
        // System.out.println("inspectNet：连接wifi");
        // } else if (netMobile == 0) {
        // System.out.println("inspectNet:连接移动数据");
        // } else if (netMobile == -1) {
        // System.out.println("inspectNet:当前没有网络");
        //
        // }
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        setStatusBar();
    }



    private SystemBarTintManager tintManager;
    /**
     * 初始化沉浸式
     */
    private void initStateBar() {
        setColorId();
        if (isNeedLoadStatusBar()) {
            loadStateBar();
        }
    }

    private void loadStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        // 设置一个状态栏颜色
        tintManager.setStatusBarTintResource(getColorId());
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 如果子类使用非默认的StatusBar,就重写此方法,传入布局的id
     */
    protected void setColorId() {
        //this.mColorId=R.color.XXX;子类重写方式
    }

    protected int getColorId() {
        return R.color.bg_green;
    }

    /**
     * 子类是否需要实现沉浸式,默认需要
     *
     * @return
     */
    protected boolean isNeedLoadStatusBar() {
        return true;
    }


    @Override
    protected void onDestroy() {
        mApplication.activities.remove(this);
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    public void aInit() {
        initViews();
        init();
        initListening();
    }

    /**
     * 初始化视图
     **/
    protected abstract void initViews();

    /**
     * 初始化数据
     **/
    protected abstract void init();

    /**
     * 初始化事件
     **/
    protected abstract void initListening();

    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        ToastUtil.showMessageShort(this, getString(resId));
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void showShortToast(String text) {
        ToastUtil.showMessageShort(this, text);
    }

    /**
     * 短暂显示Toast提示(来自String)(非主线程)
     **/
    protected void showShortToastF(String text) {
        ToastUtil.showMessageShortF(this, text);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    protected void showLongToast(int resId) {
        ToastUtil.showMessageLong(this, getString(resId));
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    protected void showLongToast(String text) {
        ToastUtil.showMessageLong(this, text);
    }


    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle带返回结果通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


}
