package org.thornfish.androidlibrary.network;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;


public  abstract class BaseCallback extends Callback<String>{

    private Activity context;

    public BaseCallback(Activity context){
        this.context = context;
    }

        @Override
        public String parseNetworkResponse(Response response, int i) throws Exception {
            final String content = response.body().string();
            if(response.isSuccessful()) {
                OkHttpUtils.getInstance().getDelivery().execute(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        try {
                            onContent(content);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                if (JSONObject.parseObject(content).getString("respcode").equals("8")) {
//                    SpUtils.saveStringSP(context,"user","uid","");
//                    SpUtils.saveStringSP(context,"user","jifen","0");
//                    SpUtils.saveStringSP(context,"user","Money","0");
//                    SpUtils.saveStringSP(context,"user","name","");
//                    SpUtils.saveStringSP(context,"user","title","");
//                    SpUtils.saveStringSP(context,"user","token","");
//                    SpUtils.saveStringSP(context, "shop", "cart", "");
//                    EMClient.getInstance().logout(true);
//                    context.startActivity(new Intent(context, LoginActivity.class));
//                    Application.getInstance().finishActivitys();
//                    Toast.makeText(context,JSONObject.parseObject(content).getString("respmsg"),Toast.LENGTH_SHORT).show();
                }
            }
                return content;
        }


    protected abstract void onContent(String content) throws Exception;

}
