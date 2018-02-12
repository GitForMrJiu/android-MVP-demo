package com.nine.mvp.ui.tabhome;

import android.os.Bundle;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleFragment;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 */

public class ForthFragment extends SimpleFragment{

    public static ForthFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ForthFragment fragment = new ForthFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.comm_ui_activity;
    }

    @Override
    protected void initEventAndData() {
//        SimpleActivity activity = (SimpleActivity) getActivity();
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
//        activity.getSupportActionBar().setTitle("我的");
    }
}
