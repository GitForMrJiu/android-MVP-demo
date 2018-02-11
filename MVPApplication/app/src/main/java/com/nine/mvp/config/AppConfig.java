package com.nine.mvp.config;

import android.content.Context;

/**
 * Created by Just For Mr.Jiu on 18/1/19.
 */

public class AppConfig {
    public static final String DB_NAME = "local_db";
    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }
}
