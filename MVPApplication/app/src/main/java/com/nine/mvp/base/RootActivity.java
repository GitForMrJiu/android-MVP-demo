package com.nine.mvp.base;

import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonSyntaxException;
import com.nine.mvp.R;
import com.nine.mvplibrary.widget.QMUI.QMUIEmptyView;

import retrofit2.HttpException;



public abstract class RootActivity<T extends BasePresenter> extends BaseActivity<T>{

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    private QMUIEmptyView emptyView;

    private ViewGroup viewMain;
    private ViewGroup mParent;


    private int currentState = STATE_MAIN;
    private boolean isErrorViewAdded = false;

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.recycleview);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'view_main'.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        mParent = (ViewGroup) viewMain.getParent();
        emptyView = mParent.findViewById(R.id.emptyView);
        viewMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateError(Throwable e) {
        if (currentState == STATE_ERROR)
            return;
//        if (!isErrorViewAdded) {
//            isErrorViewAdded = true;
//            View.inflate(mContext, mErrorResource, mParent);
//            viewError = mParent.findViewById(R.id.view_error);
//            if (viewError == null) {
//                throw new IllegalStateException(
//                        "A View should be named 'view_error' in ErrorLayoutResource.");
//            }
//        }
        hideCurrentView();
        currentState = STATE_ERROR;
        emptyView.setTitleText("加载失败");
        String err = "";
       if (e instanceof HttpException) {
            err = "数据加载失败";
           emptyView.setDetailText("数据加载失败");
        } else if (e instanceof JsonSyntaxException) {
            err = "数据解析错误";
           emptyView.setDetailText("数据解析错误");
        } else {
            err = "未知错误";
           emptyView.setDetailText("未知错误");
        }
        emptyView.show();
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        emptyView.setLoadingShowing(true);
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                emptyView.setLoadingShowing(false);
                break;
            case STATE_ERROR:
                emptyView.show();
                break;
        }
    }

//    public void setErrorResource(int errorLayoutResource) {
//        this.mErrorResource = errorLayoutResource;
//    }
}