package com.nine.mvp.ui.main.fragment;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.nine.mvp.R;
import com.nine.mvp.base.BaseTabsTabLayoutFragment;
import com.nine.mvp.bean.TabItem;
import com.nine.mvplibrary.widget.CustomTablayout;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Just For Mr.Jiu on 18/1/9.
 */

public class MainFragment extends BaseTabsTabLayoutFragment<TabItem> {
    @BindView(R.id.searchbox)
    SearchBox mSearchBox;



    @BindView(R.id.act_view)
    View viewAnimate;
    @BindView(R.id.below)
    View below;
    private float x;
    private float y;
    private int height;

    private int[] belowColor = new int[]{R.color.md_green_500, R.color.md_light_green_500
            , R.color.md_red_500, R.color.md_light_blue_500,
            R.color.md_deep_orange_500, R.color.md_blue_500, R.color.md_brown_500};

    protected OnFragmentOpenDrawerListener mOpenDraweListener;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initEventAndData() {

        Timber.e("initEventAndData");
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
//        mSearchBox.setVisibility(View.INVISIBLE);

//        TabLayout
        below.setBackgroundResource(belowColor[mCurrentPosition]);
        viewAnimate.setBackgroundResource(belowColor[mCurrentPosition]);

        mTabLayout.setLocationListener(new CustomTablayout.LocationListener() {
            @Override
            public void location(float x, float y, int tabHeight) {
                MainFragment.this.x = x;
                MainFragment.this.y = y;
                MainFragment.this.height = tabHeight;
            }
        });

        mTabLayout.addOnTabSelectedListener(new CustomTablayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabSelected(CustomTablayout.Tab tab) {
                final int position = tab.getPosition();
                Timber.e(position+"");
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



    @Override
    protected void setupTabLayout(Bundle savedInstanceSate, CustomTablayout tabLayout) {
        super.setupTabLayout(savedInstanceSate, tabLayout);

        tabLayout.setTabMode(CustomTablayout.MODE_SCROLLABLE);
    }


    @Override
    protected ArrayList<TabItem> generateTabs() {
        ArrayList<TabItem> items = new ArrayList<TabItem>();

        String[] itemArr = new String[]{"首页", "游戏", "影视", "图书", "音乐", "报亭"};
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
                return MainTabFragment.newInstance();
            // 高级
//            case 2:
//                return AdvancedItemFragment.newInstance();
            // 其他
            case 2:
                return MainTabFragment.newInstance();
        }

        return MainTabFragment.newInstance();
    }

    @Override
    protected String configLastPositionKey() {
        return "MainFragment";
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        }
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewAnimate!=null)
        viewAnimate.clearAnimation();
    }
}
