package com.nine.mvp.model;

import com.nine.mvp.model.db.DBHelper;
import com.nine.mvp.model.http.HttpHelper;
import com.nine.mvp.model.prefs.PreferencesHelper;

/**
 * Created by thinkformoney on 18/1/19.
 */

public class DataManager implements DBHelper, HttpHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper preferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.mHttpHelper = httpHelper;
        this.preferencesHelper = preferencesHelper;
        this.mDbHelper = dbHelper;
    }

}
