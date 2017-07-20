package org.thornfish.androidlibrary.network;

import android.util.Log;

import com.zhy.http.okhttp.callback.Callback;

import org.thornfish.androidlibrary.dialog.NetworkDialog;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public  class CallbackCustom extends com.zhy.http.okhttp.callback.Callback{
    private com.zhy.http.okhttp.callback.Callback callback;
    private NetworkDialog networkDialog;

        CallbackCustom(com.zhy.http.okhttp.callback.Callback callback, NetworkDialog networkDialog){
              this.networkDialog = networkDialog;
              this.callback=callback;
                if (callback==null){
                        this.callback= Callback.CALLBACK_DEFAULT;
                }
        }

        CallbackCustom(com.zhy.http.okhttp.callback.Callback callback){
              this.callback=callback;
                if (callback==null){
                        this.callback= Callback.CALLBACK_DEFAULT;
                }
        }

        @Override
        public Object parseNetworkResponse(Response response, int i) throws Exception {
            callback.parseNetworkResponse(response,i);

            return null;
        }

        @Override
        public void onError(Call call, Exception e, int i) {
                callback.onError(call,e,i);

        }

        @SuppressWarnings("unchecked")
        @Override
        public void onResponse(Object o, int i) {
                callback.onResponse(o,i);
        }

        @Override
        public void onBefore(Request request, int id) {
                callback.onBefore(request,id);
                if (networkDialog!=null&&!networkDialog.isShowing()){

                    try {
                        networkDialog.show();
                    }catch (Exception o){
                        Log.e("CallbackCustom    show", o.toString());
                    }
                }
        }

        @Override
        public void onAfter(int id) {
                callback.onAfter(id);
                if (networkDialog!=null&&networkDialog.isShowing()){

                    try {
                        networkDialog.dismiss();
                    }catch (Exception o){
                        Log.e("CallbackCustom dismiss", o.toString());
                    }
                }
        }



}
