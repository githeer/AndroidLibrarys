package org.thornfish.androidlibrary.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.Toast;


public class ToastUtil {
    private static Toast mToast;

    public ToastUtil() {
    }


    //自定义Toast提示
    public static void showMessageLong(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        mToast.show();
    }


    public static void showMessageShort(Context context, String message) {

            if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showMessageShortF(Context context, String message) {


            if("main".equals(Thread.currentThread().getName())){
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }else {
                 Looper.prepare();
                 Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                 Looper.loop();
            }

//            if (mToast != null) {
//            mToast.cancel();
//        }
//        mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
//        mToast.show();
    }

    // 自定义Toast提示1
    public static void showMyMessage(Context context, int id, String message) {
        if (mToast != null) {
            mToast.cancel();
        }
//        View viwe = View.inflate(context, R.layout.mydefined_toast, null);
//        TextView textView = (TextView) viwe.findViewById(R.id.toast_tv);
//        ImageView iv_toast = (ImageView) viwe.findViewById(R.id.iv_toast);
//        if (id == 0) {
//            iv_toast.setImageResource(R.drawable.img_my_toast_success);
//        } else if (id == -1) {
//            iv_toast.setImageResource(R.drawable.img_my_toast_error);
//        } else if (id == 2) {
//            iv_toast.setImageResource(R.drawable.img_my_toast_warning);
//        } else {
//            iv_toast.setImageResource(id);
//        }
//        mToast = new Toast(context);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
//        mToast.setView(viwe);
//        textView.setText(message);
//        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.show();
    }

    //自定义Toast提示2
    public static void showMyMessage(Context context, Bitmap id, String message) {
        if (mToast != null) {
            mToast.cancel();
        }
//        View viwe = View.inflate(context, R.layout.mydefined_toast, null);
//        TextView textView = (TextView) viwe.findViewById(R.id.toast_tv);
//        ImageView iv_toast = (ImageView) viwe.findViewById(R.id.iv_toast);
//        if (id != null) {
//            iv_toast.setImageBitmap(id);
//        }
//        mToast = new Toast(context);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
//        mToast.setView(viwe);
//        textView.setText(message);
//        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.show();
    }

}
