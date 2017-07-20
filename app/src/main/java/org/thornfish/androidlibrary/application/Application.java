package org.thornfish.androidlibrary.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.util.Log;

import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


public class Application extends android.app.Application{


    public static List<Activity> activities = new ArrayList<>();
    private static Application instance;
    private String deviceTokens;
    private int tostnumber;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Application", "----------onCreate-------------------");

        instance = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                deviceTokens = deviceToken;
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//
//        mPushAgent.setDebugMode(true);
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);

//        PlatformConfig.setWeixin("", "");
//        PlatformConfig.setSinaWeibo("", "","http://www.baidu.com");
//        PlatformConfig.setQQZone("", "");


        httpsutils();


        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("Application", "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }else{
            Log.e("Application", "----------EMOptions--------环信-----------");
            //环信
//            EMOptions options = new EMOptions();
//            EMClient.getInstance().init(getApplicationContext(), options);
//            EMClient.getInstance().setDebugMode(true);
        }
    }

//    UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//        @Override
//        public void dealWithCustomAction(Context context, UMessage msg) {
//            Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//        }
//    };

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    private void httpsutils() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12000L, TimeUnit.MILLISECONDS)
                .readTimeout(12000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    public static Application getInstance() {
        return instance;
    }


    public String token(){
        if(deviceTokens!=null) {
            return deviceTokens;
        }else{
            return "";
        }
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        activities.clear();
        super.onTerminate();
    }

    /**
     * 如果集合activities不包含该activity,并且不等于指定的这些Activity,那么就添加到集合activities中
     * && !activity.getClass().equals(MainActivity.class)
     */
    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }


    /**
     * @param
     */
    public void finishActivity() {
        for (Activity activity : activities) {
//            if (activity.getClass().equals(ShoppingCartActivity.class)||activity.getClass().equals(GoodsActivity.class))
            activity.finish();
        }
    }

    /**
     * 遍历所有Activigty并finish
     * @param
     */
    public Boolean finishActivity(Class<?> clas) {
        int istrue = 0;
        for (Activity activity : activities) {
            if (activity.getClass().equals(clas)) {
                istrue = 1;
            }
            break;
        }
        if (istrue==1)
            return true;
        else return false;
    }

    /**
     * 遍历所有Activigty并finish
     */
    public void finishActivitys() {
//        try {
//            for (int i = 0; i < activities.size(); i++) {
//                activities.get(i).finish();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            for (Activity activity : activities) {
                activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定Activigty并finish
     */
    public void finishActivity1(){
        for (Activity activity1 : activities) {
                activity1.finish();
        }
        System.exit(0);
    }

    public int getTostnumber() {
        return tostnumber;
    }

    public void setTostnumber(int tostnumber) {
        this.tostnumber = tostnumber;
    }
}
