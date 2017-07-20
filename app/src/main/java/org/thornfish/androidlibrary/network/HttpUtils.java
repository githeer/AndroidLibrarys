package org.thornfish.androidlibrary.network;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import org.thornfish.androidlibrary.dialog.NetworkDialog;
import org.thornfish.androidlibrary.utils.SpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.Request;


/**
 * @author tank
 * @time 2017/3/18  17:06
 * @todo
 */


public class HttpUtils {

    private Context context;
    private String url;
    private Map<String, String> params;
    private Callback callback;
    private String message;
    private NetworkDialog networkDialog;

    public void get(Context activity, String  url, Map<String, String> params,
                    Callback callback){

        OkHttpUtils
                .get()
                .url(url)
                .tag(activity)
                .params(params)
                .build()
                .execute(callback);
    }


    public void post(Context activity, String  url, Map<String, String> params,
                 final Callback callback){
        try {

            networkDialog = new NetworkDialog(activity);

            CallbackCustom callbacks = new CallbackCustom(callback,networkDialog);
            OkHttpUtils
                    .post()
                    .url(url)
                    .tag(activity)
                    .params(params)
                    .build()
                    .execute(callbacks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void versionpost(Context activity, String  url, Map<String, String> params,
                 final Callback callback){
        try {

            CallbackCustom callbacks = new CallbackCustom(callback);
            OkHttpUtils
                    .post()
                    .url(url)
                    .tag(activity)
                    .params(params)
                    .build()
                    .execute(callbacks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postString(Context activity, String  url, Map<String, String> params, Callback callback)
    {
        OkHttpUtils
                .postString()
                .url(url)
                .tag(activity)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content(FastJsonUtils.beanToJson(new ItemModel()))
                .build()
                .execute(callback);

    }

    public void postFile(Context activity, String  url, String  image, Map<String, String> params, Callback callback)
    {

        if(!SpUtils.getStringSP(activity,"user","token").isEmpty()){
            params.put("verifytoken",SpUtils.getStringSP(activity,"user","token"));
        }
        File file = new File(Environment.getExternalStorageDirectory(), image);
        if (!file.exists())
        {
            Toast.makeText(activity, "文件不存在!", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(url)
                .addFile("image","title.jpg",file)
                .params(params)
                .build()
                .execute(callback);


    }

    public void getImage(Context activity, String  url, BitmapCallback callback)
    {
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(activity)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(callback);
//                .execute(new BitmapCallback()
//                {
//                    @Override
//                    public void onError(Call call, Exception e, int id)
//                    {
//                    }
//
//                    @Override
//                    public void onResponse(Bitmap bitmap, int id)
//                    {
//                    }
//                });
    }


    public void uploadFile(Context activity, String  url, Map<String, String> params,File file, Callback callback)
    {
        if (!file.exists())
        {
            Toast.makeText(activity, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        OkHttpUtils.post()//
                .addFile("mFile", "messenger_01.png", file)
                .url(url)//
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(callback);
    }


    public void multiFileUpload(Context activity, String url, Map<String, String> params, File file, String picname, String picnames, Callback callback)
    {
//        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
//        File file2 = new File(Environment.getExternalStorageDirectory(), "test1#.txt");
        if (!file.exists())
        {
            Toast.makeText(activity, "图片不存在，请重新设置", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.post()//
                .addFile(picname, picnames, file)
                .url(url)
                .params(params)//
                .build()//
                .execute(callback);
    }


    public void downloadFile(Context activity, String  url, Map<String, String> params, Callback callback)
    {
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar")//
                {

                    @Override
                    public void onBefore(Request request, int id)
                    {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {
//                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(okhttp3.Call call, Exception e, int i) {

                    }



                    @Override
                    public void onResponse(File file, int id)
                    {
//                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }


    public void otherRequestDemo(Context activity, String  url, Map<String, String> params, Callback callback)
    {
        //also can use delete ,head , patch
        /*
        OkHttpUtils
                .put()//
                .url("http://11111.com")
                .requestBody
                        ("may be something")//
                .build()//
                .execute(callback);



        OkHttpUtils
                .head()//
                .url(url)
                .addParams("name", "zhy")
                .build()
                .execute();

       */


    }

    public void clearSession(Context activity, String  url, Map<String, String> params, Callback callback)
    {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl)
        {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }


}
