package com.nine.mvp.ui.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nine.mvp.base.BaseTabsTabLayoutFragment;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.bean.TabItem;
import com.nine.mvplibrary.widget.CustomTablayout;

import java.util.ArrayList;

/**
 * Created by Just For Mr.Jiu on 18/2/2.
 */

public class SettingsPagerFragment extends BaseTabsTabLayoutFragment<TabItem> {

//    @Override
//    public int inflateContentView() {
//        return R.layout.ui_settings_tabs;
//    }


    public static SettingsPagerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SettingsPagerFragment fragment = new SettingsPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupTabLayout(Bundle savedInstanceSate, CustomTablayout tabLayout) {
        super.setupTabLayout(savedInstanceSate, tabLayout);

        tabLayout.setTabMode(CustomTablayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initEventAndData() {
        setHasOptionsMenu(true);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SimpleActivity activity = (SimpleActivity) getActivity();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setTitle("设置");
    }

    @Override
    protected ArrayList<TabItem> generateTabs() {
        ArrayList<TabItem> items = new ArrayList<TabItem>();

        String[] itemArr = new String[]{"基础", "其他"};
        int index = 1;
        for (String item : itemArr) {
            TabItem bean = new TabItem();
            bean.setTitle(item);
            bean.setType(String.valueOf(index++));
            items.add(bean);
        }

        return items;
    }

    @Override
    protected Fragment newFragment(TabItem bean) {
        int index = Integer.parseInt(bean.getType());

        switch (index) {
            // 基本
            case 1:
                return BasicItemSettingsFragment.newInstance();
            // 其他
            case 2:
                return OtherItemFragment.newInstance();
        }

        return BasicItemSettingsFragment.newInstance();
    }

    @Override
    protected String configLastPositionKey() {
        return "Settings";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}