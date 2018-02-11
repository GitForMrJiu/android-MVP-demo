package com.nine.mvp.ui.about;

import android.graphics.Color;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.base.SimpleFragment;

/**
 * Created by Just For Mr.Jiu on 18/2/11.
 */

public class AboutFragment extends SimpleFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initEventAndData() {
        SimpleActivity activity = (SimpleActivity) getActivity();
        activity.getSupportActionBar().setTitle("关于");
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        getToolbar().setBackgroundColor(Color.TRANSPARENT);
    }


    protected int setActivityTheme() {
        return R.style.AppTheme_Pics;
    }

}
