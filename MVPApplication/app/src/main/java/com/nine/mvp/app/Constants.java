package com.nine.mvp.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Just For Mr.Jiu on 18/1/8.
 */

public class Constants {
    //================= KEY ====================
    public static final String BUGLY_ID = "9479bae86c";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "nine" + File.separator + "MVP";
}
