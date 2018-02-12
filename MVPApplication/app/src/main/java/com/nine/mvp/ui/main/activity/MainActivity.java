package com.nine.mvp.ui.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.nine.mvp.R;
import com.nine.mvp.base.CommonActivity;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.base.SimpleFragment;
import com.nine.mvp.ui.about.AboutFragment;
import com.nine.mvp.ui.main.fragment.MainFragment;
import com.nine.mvp.ui.setting.SettingsPagerFragment;
import com.nine.mvp.ui.tabhome.TabHomeFragment;

import butterknife.BindView;

/**
 * Created by Just For Mr.Jiu on 18/1/8.
 */

public class MainActivity extends SimpleActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentOpenDrawerListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);

        SimpleFragment fragment = findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
        setSwipeBackEnable(false);
        setBarStyle();
    }

    public void setBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
//            local LayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getActivity().getWindow().setStatusBarColor(belowColor[mCurrentPosition]);
//        }
//        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

    }

    @Override
    public void onResume() {
        super.onResume();

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, (SearchBox) findViewById(R.id.searchbox), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

        drawer.closeDrawer(GravityCompat.START);
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (item.getItemId() == R.id.setting) {
                    CommonActivity.launch(MainActivity.this, SettingsPagerFragment.class, null);

                } else if (item.getItemId() == R.id.about) {
                    CommonActivity.launch(MainActivity.this, AboutFragment.class, null);
                } else if (item.getItemId() == R.id.menu_home) {
                    CommonActivity.launch(MainActivity.this, TabHomeFragment.class, null);
                }
            }
        }, 250);

        return false;
    }

    @Override
    public void onBackPressedSupport() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                super.onBackPressedSupport();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onOpenDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }
}
