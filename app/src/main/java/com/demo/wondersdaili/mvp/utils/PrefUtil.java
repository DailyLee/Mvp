package com.demo.wondersdaili.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daili on 2017/5/5.
 */

public class PrefUtil {


    public static List<String> getListString(Context context, String key) {
        List<String> datalist = new ArrayList<>();
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (sharedPreferences != null) {
                String result = sharedPreferences.getString(key, null);
                if (result == null) {
                    return datalist;
                }
                Gson gson = new Gson();
                datalist = gson.fromJson(result, new TypeToken<List<String>>() {
                }.getType());
            }
        }
        return datalist;
    }

    public static void putListString(Context context, String key, List<String> value) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            Gson gson = new Gson();
            //转换成json数据，再保存
            String strJson = gson.toJson(value);
            edit.clear();
            edit.putString(key, strJson);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    public static String getString(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString(key, null);
                return string == null ? value : string;
            }
        }
        return value;
    }

    public static void putString(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            //edit.clear();
            edit.putString(key, value);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }
}
