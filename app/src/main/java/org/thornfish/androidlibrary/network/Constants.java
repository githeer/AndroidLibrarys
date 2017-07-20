package org.thornfish.androidlibrary.network;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @name Constants
 * @class describe
 * @anthor tank QQ:297890301
 * @time 2017/7/20 14:16
 * @change
 * @chang time
 * @class describe
 */

public class Constants {
    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "";

    public static final String APPEN = "thornfish123...";



    public static  String baseUrl = "";

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取随机6位数
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getSixNumber() {
        Random random = new Random();
        int x = random.nextInt(899999);
        int x1 = x+100000;
        return String.valueOf(x1);
    }


    /*原生接口*/
    public static final String get_login = baseUrl + "";//登录
    public static final String get_msm = baseUrl + "";//获取验证码
    public static final String get_recharge = baseUrl + "";// 支付宝支付


}
