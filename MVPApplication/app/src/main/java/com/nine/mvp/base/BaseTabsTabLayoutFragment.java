package com.nine.mvp.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.nine.mvp.R;
import com.nine.mvp.bean.TabItem;
import com.nine.mvplibrary.widget.CustomTablayout;

/**
 * Created by Just For Mr.Jiu on 18/2/2.
 */

public abstract class BaseTabsTabLayoutFragment<T extends TabItem> extends BaseTabsFragment<T> {
    protected CustomTablayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_ui_tabs_tablayout;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mTabLayout = (CustomTablayout)mView.findViewById(R.id.tabLayout);
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    final protected void setupViewPager(Bundle savedInstanceSate) {
        setupTabLayout(savedInstanceSate, mTabLayout);

    }
    protected void setupTabLayout(Bundle savedInstanceSate, final CustomTablayout tabLayout) {
        super.setupViewPager(savedInstanceSate);

        tabLayout.setTabMode(CustomTablayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.parseColor("#b3ffffff"), Color.WHITE);
        tabLayout.setupWithViewPager(getViewPager());
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                tabLayout.setScrollPosition(mCurrentPosition, 0, true);
            }

        }, 150);
    }

    public CustomTablayout getTablayout() {
        return mTabLayout;
    }

    protected void setTabLayout(CustomTablayout tabLayout) {
        mTabLayout = tabLayout;
    }
}
