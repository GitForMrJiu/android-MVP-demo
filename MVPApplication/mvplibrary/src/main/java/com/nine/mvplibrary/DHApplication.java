package com.nine.mvplibrary;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.nine.mvplibrary.setting.AppSettings;
import com.nine.mvplibrary.setting.SettingUtility;
import com.nine.mvplibrary.utils.ThemeConstants;

import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by thinkformoney on 17/8/8.
 */

public class DHApplication extends MultiDexApplication implements ThemeUtils.switchColor {

    private static DHApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
//        SystemUtils.getScreenWidth(this);
        ThemeUtils.setSwitchColor(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/Miui-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        SettingUtility.addSettings(this, "settings");
        // 初始化一个颜色主题
        setupTheme();
    }

    private void setupTheme() {
        int position = AppSettings.getThemeColor();
        if (position == -1) {
            // 一些我喜欢的颜色
            int[] initIndex = new int[]{0, 1, 4, 8, 15, 16, 18};
            position = initIndex[new Random().nextInt(initIndex.length)];

            AppSettings.setThemeColor(0);
        }
    }
    public static DHApplication getInstance() {
        return appContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void checkAccount() {

    }

    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        String theme = getTheme(context);
        if (theme != null) {
            colorId = getThemeColorId(context, colorId, theme);
        }
        return context.getResources().getColor(colorId);
    }

    @Override
    public int replaceColor(Context context, @ColorInt int originColor) {
        String theme = getTheme(context);
        int colorId = -1;

        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme);
        }
        return colorId != -1 ? getResources().getColor(colorId) : originColor;
    }

    private String getTheme(Context context) {
        int id = AppSettings.getThemeColor();
        if (id != -1) {

            return ThemeConstants.themeColorName[id];
        }
        return null;
    }

    private
    @ColorRes
    int getThemeColorId(Context context, int colorId, String theme) {
        if (colorId== R.color.md_red_700)
        {
            return context.getResources().getIdentifier("md_" + theme + "_700", "color", getPackageName());
        }
        return context.getResources().getIdentifier("md_" + theme + "_500", "color", getPackageName());
    }

    private
    @ColorRes
    int getThemeColor(Context context, int color, String theme) {
        return context.getResources().getIdentifier("md_" + theme + "_700", "color", getPackageName());
    }
}
