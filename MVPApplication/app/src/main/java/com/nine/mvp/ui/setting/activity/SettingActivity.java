package com.nine.mvp.ui.setting.activity;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.base.SimpleFragment;
import com.nine.mvp.ui.setting.SettingsPagerFragment;

/**
 * Created by Just For Mr.Jiu on 18/2/9.
 */

public class SettingActivity extends SimpleActivity{
    @Override
    protected int getLayout() {
        return R.layout.comm_ui_activity;
    }

    @Override
    protected void initEventAndData() {

        SimpleFragment fragment = findFragment(SettingsPagerFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, SettingsPagerFragment.newInstance());
        }

    }
}
