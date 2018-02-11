package com.nine.mvp.presenter.login;

import com.nine.mvp.base.RxPresenter;
import com.nine.mvp.base.contract.login.LoginContract;
import com.nine.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * Created by thinkformoney on 18/1/19.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {


    }

    @Override
    public void onServerLoginClick(String email, String password) {

        mView.jumpToMain();
    }

    @Override
    public void onGoogleLoginClick() {

    }

    @Override
    public void onFacebookLoginClick() {

    }
}
