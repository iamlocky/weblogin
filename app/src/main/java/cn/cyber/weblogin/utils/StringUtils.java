package cn.cyber.weblogin.utils;

import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by LockyLuo on 2018/2/6.
 */

public class StringUtils {
    private static String tag="utils";
    /***
     * 获取url 指定name的value;
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";

        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }

    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Log.d(tag, "toURLEncoded error:" + paramString);
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            Log.e(tag, "toURLEncoded error:" + paramString);
            Log.e(tag, localException.getMessage());
        }
        return "";
    }

    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Log.d(tag, "toURLDecoded error:" + paramString);
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            Log.e(tag, "toURLDecoded error:" + paramString);
            Log.e(tag, localException.getMessage());

        }
        return "";
    }
}
