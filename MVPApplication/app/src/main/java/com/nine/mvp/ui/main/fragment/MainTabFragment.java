package com.nine.mvp.ui.main.fragment;

import android.os.Bundle;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleFragment;

/**
 * Created by Just For Mr.Jiu on 18/2/2.
 */

public class MainTabFragment extends SimpleFragment{


    public static MainTabFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainTabFragment fragment = new MainTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.comm_ui_activity;
    }

    @Override
    protected void initEventAndData() {

    }
}
