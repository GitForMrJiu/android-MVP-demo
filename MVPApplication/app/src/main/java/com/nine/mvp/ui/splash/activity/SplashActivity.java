package com.nine.mvp.ui.splash.activity;

import android.Manifest;
import android.content.Intent;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.ui.login.activity.LoginActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Just For Mr.Jiu on 18/1/9.
 */

public class SplashActivity extends SimpleActivity {

    private int mIndex = 0;
    private int mCount = 6;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(new RxPermissions(this)
                        .ensureEach(Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                        if (permission.granted) {
                            mIndex++;
                            Timber.d(mIndex+"mIndex  "+permission.name);
                        }
                        mCount--;
                        Timber.d(mCount+"  "+permission.name);
                        if (mCount == 0) {
                                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                        }

                    }
                });

    }
}
