package com.nine.mvp.di.component;

import android.app.Activity;

import com.nine.mvp.di.module.FragmentModule;
import com.nine.mvp.di.scope.FragmentScope;
import com.nine.mvp.ui.main.fragment.MainFragment;

import dagger.Component;


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MainFragment mainFragment);
}
