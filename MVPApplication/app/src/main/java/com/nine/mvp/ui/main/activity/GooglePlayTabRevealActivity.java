package com.nine.mvp.ui.main.activity;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.ui.main.fragment.MainFragment;
import com.nine.mvplibrary.widget.CustomTablayout;
import com.quinny898.library.persistentsearch.SearchBox;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Just For Mr.Jiu on 18/2/9.
 */

public class GooglePlayTabRevealActivity extends SimpleActivity {
    @BindView(R.id.searchbox)
    SearchBox mSearchBox;
    @BindView(R.id.tabLayout)
    CustomTablayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private SimpleFragmentPagerAdapter pagerAdapter1;
    @BindView(R.id.act_view)
    View viewAnimate;
    @BindView(R.id.below)

    protected MainFragment.OnFragmentOpenDrawerListener mOpenDraweListener;
    View below;
    private float x;
    private float y;
    private int height;

    private int[] belowColor = new int[]{R.color.md_green_500, R.color.md_light_green_500
            , R.color.md_red_500, R.color.md_light_blue_500,
            R.color.md_deep_orange_500, R.color.md_blue_500, R.color.md_brown_500};

    protected int getLayout() {
        return R.layout.activity_google_play_tab_reveal;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setBarStyle();
    }

    /**
     * 获取系统状态栏高度
     *
     * @param context context
     * @return statusBar height
     */
    public static int getSystemStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    protected void initEventAndData() {
        mSearchBox.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {

                if (mOpenDraweListener != null) {
                    mOpenDraweListener.onOpenDrawer();
                }
            }
        });
        mSearchBox.enableVoiceRecognition(this);
        mSearchBox.setLogoText("MVP初体验");
        mSearchBox.setHint("搜索内容");
        pagerAdapter1 = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), new
                String[]{"tab1", "tab2", "tab3", "tab4"});
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(CustomTablayout.MODE_FIXED);

        viewPager.setAdapter(pagerAdapter1);
        below.setBackgroundResource(belowColor[0]);
        viewAnimate.setBackgroundResource(belowColor[0]);

        tabLayout.setLocationListener(new CustomTablayout.LocationListener() {
            @Override
            public void location(float x, float y, int tabHeight) {
                GooglePlayTabRevealActivity.this.x = x;
                GooglePlayTabRevealActivity.this.y = y;
                GooglePlayTabRevealActivity.this.height = tabHeight;
            }
        });

        tabLayout.addOnTabSelectedListener(new CustomTablayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabSelected(CustomTablayout.Tab tab) {
                final int position = tab.getPosition();
                Timber.e(position + "");
                Log.e("test", "x: " + x + "      y: " + y + "    height: " + height);
                final int width = viewAnimate.getWidth();
                final int height = viewAnimate.getHeight();
                final double radio = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
                float centerX = x;
                float centerY = y;
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(viewAnimate,
                        (int) centerX,
                        (int) centerY, 0, (float) radio);
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.setDuration(375);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        viewAnimate.setBackgroundResource(belowColor[position]);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        below.setBackgroundResource(belowColor[position]);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                circularReveal.start();

            }

            @Override
            public void onTabUnselected(CustomTablayout.Tab tab) {

            }

            @Override
            public void onTabReselected(CustomTablayout.Tab tab) {

            }
        });
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[];

        public SimpleFragmentPagerAdapter(FragmentManager fm, String[] strings) {
            super(fm);
            tabTitles = strings;
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment_demo.newInstance();
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    public void setBarStyle() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        } else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
