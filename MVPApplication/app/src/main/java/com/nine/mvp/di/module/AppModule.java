package com.nine.mvp.di.module;


import com.nine.mvp.app.App;
import com.nine.mvp.model.DataManager;
import com.nine.mvp.model.db.DBHelper;
import com.nine.mvp.model.db.GreenDaoHelper;
import com.nine.mvp.model.http.HttpHelper;
import com.nine.mvp.model.http.RetrofitHelper;
import com.nine.mvp.model.prefs.ImplPreferencesHelper;
import com.nine.mvp.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(GreenDaoHelper daoHelper) {
        return daoHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, dbHelper, preferencesHelper);
    }
}
