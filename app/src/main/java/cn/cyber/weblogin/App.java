package cn.cyber.weblogin;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


import java.lang.reflect.Field;


/**
 * Created by XQM on 2017/11/15.
 */

public class App extends Application {
    private static App instance;
    public static AndroidLogAdapter androidLogAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (androidLogAdapter == null) {
            androidLogAdapter = new AndroidLogAdapter(getFormatStrategy());
            Logger.addLogAdapter(androidLogAdapter);
        }
    }

    public static App getInstance() {
        return instance;
    }

    private FormatStrategy getFormatStrategy() {
        FormatStrategy formatStrategy = PrettyFormatStrategy
                .newBuilder()
                .tag("locky Log").build();
        return formatStrategy;
    }
}
