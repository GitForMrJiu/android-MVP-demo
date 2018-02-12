package com.nine.mvp.model;

import com.nine.mvp.model.db.DBHelper;
import com.nine.mvp.model.http.HttpHelper;
import com.nine.mvp.model.prefs.PreferencesHelper;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
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
