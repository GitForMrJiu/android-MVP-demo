package com.nine.mvp.presenter.login;

import com.nine.mvp.base.RxPresenter;
import com.nine.mvp.base.contract.login.LoginContract;
import com.nine.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
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
