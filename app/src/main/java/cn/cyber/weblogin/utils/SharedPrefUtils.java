package cn.cyber.weblogin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.cyber.weblogin.App;


/**
 * Created by LockyLuo on 2017/9/17.
 */

public class SharedPrefUtils {
    public static String tag = "pre";

    public static void save(String name, String data) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, data);
        editor.commit();
    }

    public static String load(String name) {
        String data = "";
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(tag, Context.MODE_PRIVATE);
        data = sharedPreferences.getString(name, data);
        return data;
    }

    public static void clear() {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void remove(String key) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
