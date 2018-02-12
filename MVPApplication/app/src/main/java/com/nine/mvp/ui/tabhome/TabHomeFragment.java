package com.nine.mvp.ui.tabhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.nine.mvp.R;
import com.nine.mvp.base.SimpleActivity;
import com.nine.mvp.base.SimpleFragment;
import com.nine.mvp.widget.BottomBar;
import com.nine.mvp.widget.BottomBarTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 */

public class TabHomeFragment extends SimpleFragment {
    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;

    List<Fragment> fragments;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    public static TabHomeFragment newInstance() {

        Bundle args = new Bundle();

        TabHomeFragment fragment = new TabHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tabhome;
    }

    @Override
    protected void initEventAndData() {
        SimpleActivity activity = (SimpleActivity) getActivity();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setTitle("首页");
        initUI();

        fragments = new ArrayList<>();
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
        fragments.add(ThirdFragment.newInstance());
        fragments.add(ForthFragment.newInstance());

        MainAdapter mainAdapter = new MainAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(mainAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

//                lastSelectedPosition = position;
                mBottomBar.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initUI() {


        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_message_white_24dp, "主页"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_discover_white_24dp, "朋友圈"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_home_white_24dp, "邮件"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_account_circle_white_24dp, "我的"));


        // 模拟未读消息
        mBottomBar.getItem(0).setUnreadCount(9);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
//                showHideFragment(mFragments[position], mFragments[prePosition]);
//
//                BottomBarTab tab = mBottomBar.getItem(FIRST);
//                if (position == FIRST) {
//                    tab.setUnreadCount(0);
//                } else {
//                    tab.setUnreadCount(tab.getUnreadCount() + 1);
//                }
                viewPager.setCurrentItem(position, true);
                SimpleActivity activity = (SimpleActivity) getActivity();
                activity.getSupportActionBar().setTitle(mBottomBar.getItem(position).getTitle());
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }

    public class MainAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments;

        public MainAdapter(FragmentManager fm, List<Fragment> fragments) {

            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 4;
        }


    }
}
