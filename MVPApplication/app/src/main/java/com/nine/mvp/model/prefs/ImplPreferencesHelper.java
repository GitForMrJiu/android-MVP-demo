package com.nine.mvp.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.nine.mvp.app.App;

import javax.inject.Inject;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
 */

public class ImplPreferencesHelper implements PreferencesHelper{

    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
