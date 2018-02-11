package com.nine.mvp.base;

/**
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError(Throwable e);

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
