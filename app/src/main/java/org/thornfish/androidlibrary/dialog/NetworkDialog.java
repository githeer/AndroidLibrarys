package org.thornfish.androidlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;

import org.thornfish.androidlibrary.R;


/**
 * @name NetworkDialog
 * @class 等待弹窗
 * @anthor tank QQ:297890301
 * @time 2017/3/23 11:27
 * @change
 * @chang time
 * @class describe
 */

public class NetworkDialog extends Dialog{


    public NetworkDialog(@NonNull Context context) {
        super(context, R.style.popProgressDialog);
    }

    public NetworkDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.popProgressDialog);
    }

    protected NetworkDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
    //    @SuppressWarnings("rawtypes")
//    public NetworkDialog createDialog(Context context) {
//        dialog = new NetworkDialog(context, R.style.popProgressDialog);
//        dialog.setContentView(R.layout.dialog_loading);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        return dialog;
//    }

}
