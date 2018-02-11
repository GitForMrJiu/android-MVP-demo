package com.nine.mvplibrary.setting;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nine.mvplibrary.DHApplication;
import com.nine.mvplibrary.R;
import com.nine.mvplibrary.utils.ActivityHelper;


/**
 * Created by thinkformoney on 16/8/18.
 */
public class AppSettings {
    static final int[] countArr = { 20, 50, 100 };

    public static final int REQUEST_DATA_DELAY = 500;

    public static int getPublishDelay() {
        return 5 * 1000;
    }

    /**
     * 应用常驻内存
     *
     * @return
     */
    public static boolean isAppResident() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        return prefs.getBoolean("pAppResident", false);
    }
    /**
     * 是否使用内置浏览器
     *
     * @return
     */
    public static boolean isInnerBrower() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        return prefs.getBoolean("pInnerBrowser", true);
    }

    /**
     * 正文字体大小
     *
     * @return
     */
    private static int[] txtSizeResArr = new int[]{R.dimen.sp_12, R.dimen.sp_13, R.dimen.sp_14, R.dimen.sp_15,
                                                   R.dimen.sp_16, R.dimen.sp_17, R.dimen.sp_18, R.dimen.sp_19,
                                                   R.dimen.sp_20 };
    public static int getTextSize() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        int value = Integer.parseInt(prefs.getString("pTextSize", "4"));
        return DHApplication.getInstance().getResources().getDimensionPixelSize(txtSizeResArr[value]);
    }
    /**
     * 开发者测试模式
     *
     * @return
     */
    public static boolean isDebug() {
        // 自动刷新时间间隔为30秒
        // 屏幕旋转
        // 打开音效

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        return prefs.getBoolean("pDebug", false);
    }

    /**
     * 关闭缓存
     *
     * @return
     */
    public static boolean isDisableCache() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        return prefs.getBoolean("pDisableCache", false);
    }

    public static String getImageSavePath() {
        return ActivityHelper.getShareData(DHApplication.getInstance(), "com.linkstart.intelligentfire.Images", "Images");
    }

    public static void setImageSavePath(String path) {
        ActivityHelper.putShareData(DHApplication.getInstance(), "com.linkstart.intelligentfire.Images", path);
    }

    /**
     * 手势返回方向设置
     *
     * @return
     */
//    public static int getSwipebackEdgeMode() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(HzApplication.getInstance());
//        return Integer.parseInt(prefs.getString("pSwipebackEdgeMode", "0"));
//    }
    public static int getThemeColor() {
        return ActivityHelper.getIntShareData(DHApplication.getInstance(), "Theme_index", -1);
    }

    public static void setThemeColor(int theme) {
        ActivityHelper.putIntShareData(DHApplication.getInstance(), "Theme_index", theme);
    }
    /**
     * 屏幕旋转
     *
     * @return
     */
    public static boolean isScreenRotate() {
        if (isDebug())
            return true;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DHApplication.getInstance());
        return prefs.getBoolean("pScreenRotate", false);
    }
}
