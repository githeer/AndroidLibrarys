package org.thornfish.androidlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yike.wawaji.Interface.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class SpUtils {

    /**
     * SP存储工具类可以存储的数据类型：boolean,int long String Float
     */

    public static void saveBooleanSP(Context context, String path, String key,
                                     boolean value) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanSP(Context context, String path, String key) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void saveStringSP(Context context, String path, String key,
                                    String value){
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        try {
            sp.edit().putString(key, AES.encrypt(Constants.APPEN, value)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getStringSP(Context context, String path, String key) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        String decrypt = "";
        try {
            decrypt = AES.decrypt(Constants.APPEN, sp.getString(key, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt;
    }

    public static void saveIntSP(Context context, String path, String key,
                                 int value) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static int getIntSP(Context context, String path, String key) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    public static void saveFloatSP(Context context, String path, String key,
                                   float value) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }

    public static float getFloatSP(Context context, String path, String key) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    public static void saveLongSP(Context context, String path, String key,
                                  long value) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    public static float getLongSP(Context context, String path, String key) {
        SharedPreferences sp = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    public static void saveInfo(Context context, String key, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {

            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {

                }
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    public static List<Map<String, String>> getInfo(Context context, String key) {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {

        }

        return datas;
    }

}
