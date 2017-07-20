package org.thornfish.androidlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectUtils {
    //移动是否连接
    public static boolean isMobile(Context context) {
        if (!isConnected(context)) {
            return false;
        }
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return ConnectivityManager.TYPE_MOBILE == info.getType();
    }

    //是否wifi连接
    public static boolean isWIFI(Context context) {
        if (!isConnected(context)) {
            return false;
        }
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return ConnectivityManager.TYPE_WIFI == info.getType();
    }

    //判断是否连接网络
    public static boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return true;
    }


}
