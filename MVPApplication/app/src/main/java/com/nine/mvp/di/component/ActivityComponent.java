package com.nine.mvp.di.component;

import android.app.Activity;

import com.nine.mvp.di.module.ActivityModule;
import com.nine.mvp.di.scope.ActivityScope;
import com.nine.mvp.ui.login.activity.LoginActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

//    LoginActivity getLoginActivity();

    void inject(LoginActivity loginActivity);
}
