package com.nine.mvp.di.component;


import com.nine.mvp.app.App;
import com.nine.mvp.di.module.AppModule;
import com.nine.mvp.di.module.HttpModule;
import com.nine.mvp.model.DataManager;
import com.nine.mvp.model.db.GreenDaoHelper;
import com.nine.mvp.model.http.RetrofitHelper;
import com.nine.mvp.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心
    RetrofitHelper retrofitHelper();  //提供http的帮助类
    GreenDaoHelper greenDaoHelper();    //提供数据库帮助类
    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
