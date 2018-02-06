package cn.cyber.weblogin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.Serializable;

import cn.cyber.weblogin.App;


/**
 * Created by lockyluo on 2017/7/29.
 * activity启动工具
 * 减少代码量
 */

public class StartActivityUtil {
    private static Intent intent;


    /**
     * @param context
     * @param clazz
     */
    public static void start(Context context, Class clazz) {
        intent = new Intent(context, clazz);

        getLogAdapter();
        Logger.d("startActivity: "+clazz.getName());
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param clazz
     * @param requestCode
     */
    public static void startForResult(Activity context, Class clazz, int requestCode) {
        intent = new Intent(context, clazz);

        getLogAdapter();
        Logger.d("startActivity without data");
        context.startActivityForResult(intent, requestCode);
    }

    public static void startWithUri(Context context, Class clazz, Uri uri){
        Intent intent=new Intent(context,clazz);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param clazz
     * @param data
     */
    public static void startWithData(Context context, Class clazz, Serializable data) {
        intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtras(bundle);

        getLogAdapter();
        Logger.d("startActivity with data");
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param clazz
     * @param data 可序列化的bean数据
     * @param requestCode
     */
    public static void startForResultWithData(Activity context, Class clazz, Serializable data, int requestCode) {
        intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        intent.putExtras(bundle);

        getLogAdapter();
        Logger.d("startActivity with data");
        context.startActivityForResult(intent, requestCode);
    }

    private static FormatStrategy getFormatStrategy() {
        FormatStrategy formatStrategy = PrettyFormatStrategy
                .newBuilder()
                .tag("locky part").build();
        return formatStrategy;
    }

    private static void getLogAdapter() {
        if (App.androidLogAdapter == null) {
            App.androidLogAdapter = new AndroidLogAdapter(getFormatStrategy());
            Logger.addLogAdapter(App.androidLogAdapter);
        }
    }
}
