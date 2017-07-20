package org.thornfish.androidlibrary.network;

import android.content.Context;
import android.os.Environment;

import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;

import org.thornfish.androidlibrary.utils.Base64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tank on 2017/3/18.
 * 网络请求辅助类
 */

public class HttpPostGet {

    /**
     * 获取验证码
     */
    public static void POST_GET_SMSCODE(Context activity, String phone, Callback listener) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        new HttpUtils().post(activity, Constants.get_msm, params,
                listener);

    }

    /**
     * 用户登录
     */
    public static void POST_GET_LOGIN(Context activity, String phone, String code, String type, String tokens, Callback listener) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("code", code);
        params.put("type", type);
        params.put("tokens", tokens);
        new HttpUtils().versionpost(activity, Constants.get_login, params,
                listener);

    }


    /**
     * 签名接口支付宝
     */
    public static void POST_GET_AlIPAY(Context activity, String uid, String money, String type, Callback listener) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("money", money);
        params.put("type", type);
        new HttpUtils().post(activity, Constants.get_recharge, params,
                listener);
    }

    /**
     * 签名接口微信
     */
    public static void POST_GET_WECHAT(Context activity, String id, String partner, String out_trade_no, String body,
                                       String detail, String total_fee, Callback listener) {

        Map<String, String> params = new HashMap<>();
        params.put("USER_ID", id);
        params.put("PARTNER", partner);
        params.put("OUT_TRADE_NO", out_trade_no);
        params.put("BODY", body);
        params.put("DETAIL", detail);
        params.put("TOTAL_FEE", total_fee);
//        new HttpUtils().post(activity, Constants.get_userinfo, params,
//                listener);
    }



    /**
     * 用户信息修改
     */
    public static void POST_HEADPHOTO(Context activity, String id, String name, String picname, Callback listener) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("USER_ID", id);
        params.put("USER_NAME", name);
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" +picname);
        try {
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            params.put("USER_PIC_URL", Base64Encoder.encode(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        new HttpUtils().post(activity, Constants.get_revise_info, params,
//                listener);
//        new HttpUtils().multiFileUpload(activity, Constants.get_revise_info, params, file, "USER_PIC_URL", picname,
//                listener);
    }


    /**
     * 头像读取
     */

    public static void POST_GET_PIC(Context activity, String url, BitmapCallback callback) {

        new HttpUtils().getImage(activity, url ,callback);
    }
    
}
