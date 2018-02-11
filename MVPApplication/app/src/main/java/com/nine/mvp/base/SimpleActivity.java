package com.nine.mvp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.nine.mvp.R;
import com.nine.mvp.app.App;
import com.nine.mvp.utils.SystemUtil;
import com.nine.mvplibrary.setting.AppSettings;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SwipeBackActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;
    private int theme = -1;// 当前界面设置的主题

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (theme != AppSettings.getThemeColor()) {
            theme = AppSettings.getThemeColor();
            ThemeUtils.refreshUI(this, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                ActivityManager.TaskDescription taskDescription =
                                        new ActivityManager.TaskDescription(null, null,
                                                ThemeUtils.getThemeAttrColor(SimpleActivity.this, R.attr.themeColor));
                                SimpleActivity.this.setTaskDescription(taskDescription);
                                SimpleActivity.this.getWindow().setStatusBarColor(
                                        ThemeUtils.getColorById(SimpleActivity.this, R.color.md_red_700));
                                getWindow().setNavigationBarColor(
                                        ThemeUtils.getThemeAttrColor(SimpleActivity.this, R.attr.themeColor));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view) {
                            //TODO: will do this for each traversal
                        }
                    }
            );
        }

    }
    /**
     * 设置动画，也可以使用setFragmentAnimator()设置
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        SystemUtil.fixInputMethodManagerLeak(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    @Override
    protected void attachBaseContext(Context newBase) {

        // TODO Auto-generated method stub

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
