package com.nine.mvp.ui.login.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.nine.mvp.R;
import com.nine.mvp.base.BaseActivity;
import com.nine.mvp.base.contract.login.LoginContract;
import com.nine.mvp.presenter.login.LoginPresenter;
import com.nine.mvp.ui.main.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Just For Mr.Jiu on 18/1/19.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.passlayout)
    TextInputLayout passlayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        passlayout.setTypeface(Typeface.createFromAsset(getAssets(),
                "fonts/source-sans-pro/Miui-Regular.ttf"));
    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        mPresenter.onServerLoginClick(et_email.getText().toString(),
                et_password.getText().toString());
    }

    @OnClick(R.id.ib_google_login)
    void onGoogleLoginClick(View v) {
        mPresenter.onGoogleLoginClick();
    }

    @OnClick(R.id.ib_fb_login)
    void onFbLoginClick(View v) {
        mPresenter.onFacebookLoginClick();
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
