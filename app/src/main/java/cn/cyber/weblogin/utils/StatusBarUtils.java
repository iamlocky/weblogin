package cn.cyber.weblogin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.cyber.weblogin.R;


/**
 * Created by LockyLuo on 2018/1/23.
 */

public class StatusBarUtils {

    public static void colorNormal(Activity activity, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //4.4.2~4.4W
            try {
                String[] release = Build.VERSION.RELEASE.split("\\.");
                int last = Integer.parseInt(release[release.length - 1]);
                if (last >= 2) {
                    setStatusBarColorOldSDK(activity, color);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.+~
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
            window.setNavigationBarColor(activity.getResources().getColor(color));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.+~
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
    }

    public static void colorNormalByColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //4.4.2~4.4W
            try {
                String[] release = Build.VERSION.RELEASE.split("\\.");
                int last = Integer.parseInt(release[release.length - 1]);
                if (last >= 2) {
                    setStatusBarColorOldSDKByColor(activity, color);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.+~
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.+~
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
    }


    public static void colorLight(Activity activity, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //4.4.2~4.4W
            try {
                String[] release = Build.VERSION.RELEASE.split("\\.");
                int last = Integer.parseInt(release[release.length - 1]);
                if (last >= 2) {
                    setStatusBarColorOldSDK(activity, R.color.colorPrimary);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //5.+
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
            window.setNavigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.+~
            //浅色背景时设置深色字体,仅安卓6.0以上支持
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ActivityCompat.getColor(activity, color));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setNavigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        }
    }

    public static void colorLightByColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //4.4.2~4.4W
            try {
                String[] release = Build.VERSION.RELEASE.split("\\.");
                int last = Integer.parseInt(release[release.length - 1]);
                if (last >= 2) {
                    setStatusBarColorOldSDK(activity, R.color.colorPrimary);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //5.+
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
            window.setNavigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.+~
            //浅色背景时设置深色字体,仅安卓6.0以上支持
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setNavigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        }
    }

    public static void setStatusBarColorOldSDK(Activity activity, @ColorRes int colorId) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup decorViewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View statusBarView = new View(activity);
        int statusBarHeight = getStatusBarHeight(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(activity.getResources().getColor(colorId));
        decorViewGroup.addView(statusBarView);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(true);
        }
    }

    public static void setStatusBarColorOldSDKByColor(Activity activity, @ColorInt int color) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup decorViewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View statusBarView = new View(activity);
        int statusBarHeight = getStatusBarHeight(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        decorViewGroup.addView(statusBarView);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(true);
        }
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    public static void showMenuButton(Window window) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            showNavigationIceCreamSandwich(window);
        } else {
            showNavigationLollipopMR1(window);
        }
    }

    /**
     * 显示虚拟导航栏菜单按钮.
     * Android 4.0 - Android 5.0
     * API 14 - 21
     *
     * @param window {@link Window}
     */
    private static void showNavigationIceCreamSandwich(Window window) {
        try {
            int flags = WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null);
            window.addFlags(flags);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示虚拟导航栏菜单按钮.
     * Android 5.1.1 - Android 7.0，Android 8.0 未测试
     * API 22 - 25
     *
     * @param window {@link Window}
     */
    private static void showNavigationLollipopMR1(Window window) {
        try {
            Method setNeedsMenuKey = Window.class.getDeclaredMethod("setNeedsMenuKey", int.class);
            setNeedsMenuKey.setAccessible(true);
            int value = WindowManager.LayoutParams.class.getField("NEEDS_MENU_SET_TRUE").getInt(null);
            setNeedsMenuKey.invoke(window, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
