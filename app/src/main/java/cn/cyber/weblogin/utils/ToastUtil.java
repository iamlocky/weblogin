package cn.cyber.weblogin.utils;

import android.util.Log;
import android.widget.Toast;

import cn.cyber.weblogin.App;



public class ToastUtil {
    private static Toast mToast;//控制toast时间
    private static Toast toast;
    public static boolean isStop=false;

    public static void show(String text) {
        if (isStop)
            return;
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

}
