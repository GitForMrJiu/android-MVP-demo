package com.nine.mvp.base.contract.login;

import com.nine.mvp.base.BasePresenter;
import com.nine.mvp.base.BaseView;

/**
 * Created by Just For Mr.Jiu on 18/1/19.
 */

public interface LoginContract  {
    interface View extends BaseView {

        void jumpToMain();


    }

    interface  Presenter extends BasePresenter<View> {

        void getWelcomeData();

        void onServerLoginClick(String email, String password);

        void onGoogleLoginClick();

        void onFacebookLoginClick();
    }
}
