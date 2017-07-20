package org.thornfish.androidlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

import org.thornfish.androidlibrary.dialog.MyAlertDialog;


/**
 * 拨打电话
 *
 * @author Administrator
 */
public class CallPhone {

    public static void Call_Phone(final Context context, final String phone) {
        new MyAlertDialog.Builder(context, 0).setMessage("是否现在拨打" + phone)
                .setTitle("提示")
                .setPositiveButton("拨打", new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        //获取调用拨打电话意图对象
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri
                                .parse("tel:" + phone));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }, true).setNegativeButton("取消", null, true).show();
    }

}
