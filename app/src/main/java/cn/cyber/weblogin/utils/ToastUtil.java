package cn.cyber.weblogin.utils;

import android.util.Log;
import android.widget.Toast;

import cn.cyber.weblogin.App;



public class ToastUtil {
    private static Toast mToast;//控制toast时间
    private static Toast toast;

    public static void show(String text) {
        if (text==null)
            text="";
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
        Log.i("Toast", "--------------------------------");
        Log.i("Toast", text);
    }

    public static void showShort(String txt){
        Toast.makeText(App.getInstance(),txt, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String txt){
        Toast.makeText(App.getInstance(),txt, Toast.LENGTH_LONG).show();
    }
}
