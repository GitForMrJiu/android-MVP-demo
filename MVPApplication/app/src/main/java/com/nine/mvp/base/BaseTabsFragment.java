package com.nine.mvp.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.nine.mvp.R;
import com.nine.mvp.app.App;
import com.nine.mvp.base.adapter.FragmentPagerAdapter;
import com.nine.mvp.bean.TabItem;
import com.nine.mvplibrary.utils.ActivityHelper;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by Just For Mr.Jiu on 18/2/2.
 */

public abstract class BaseTabsFragment<T extends TabItem> extends SimpleFragment implements ViewPager.OnPageChangeListener {

    static final String TAG = "AFragment-Tabs";

    public static final String SET_INDEX = "com.nine.mvp.tab.SET_INDEX";// 默认选择第几个

    protected ViewPager mViewPager;

    protected FragmentPagerAdapter mInnerAdapter;

    protected ArrayList<T> mItems;
    protected Map<String, Fragment> fragments;
    protected int mCurrentPosition = 0;

    private boolean isRetainFragments = false;// 如果系统先调用onSaveInstanceState方法，说明添加的Fragments是需要保留的

    @Override
    protected int getLayoutId() {
        return R.layout.comm_ui_tabs;
    }

    @Override
    public void onLazyInitView(@Nullable final Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
        mItems = savedInstanceState == null ? generateTabs() : (ArrayList<T>) Parcels.unwrap(savedInstanceState.getParcelable("items"));

        mCurrentPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt("current");

        if (delayTabInit() == 0) {
            setTabInit(savedInstanceState);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    setTabInit(savedInstanceState);
                }

            }, delayTabInit());
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mCurrentPosition = mViewPager.getCurrentItem();
        outState.putParcelable("items", Parcels.wrap(mItems));
        outState.putInt("current", mCurrentPosition);

        isRetainFragments = true;
    }

    protected void setTabInit(Bundle savedInstanceSate) {
        if (getActivity() == null) {
            return;
        }
//        else if (getActivity() instanceof BaseActivity) {
//            if (((BaseActivity) getActivity()).isDestory()) {
//                return;
//            }
//        }

        if (savedInstanceSate == null) {
            if (getArguments() != null && getArguments().containsKey(SET_INDEX)) {
                mCurrentPosition = Integer.parseInt(getArguments().getSerializable(SET_INDEX).toString());
            } else {
                if (configLastPositionKey() != null) {
                    // 记录了最后阅读的标签
                    String type = ActivityHelper.getShareData(App.getInstance(), "PagerLastPosition" + configLastPositionKey(), "");
                    if (!TextUtils.isEmpty(type)) {
                        for (int i = 0; i < mItems.size(); i++) {
                            TabItem item = mItems.get(i);
                            if (type.equals(item.getType())) {
                                mCurrentPosition = i;
                                break;
                            }
                        }
                    }
                }
            }
        }

        Timber.d(TAG, "CurrentPosition " + mCurrentPosition);

        fragments = new HashMap<>();

        // 初始化的时候，移除一下Fragment
        if (savedInstanceSate == null) {
            for (int i = 0; i < mItems.size(); i++) {
                Fragment fragment = getChildFragmentManager().findFragmentByTag(makeFragmentName(i));
                if (fragment != null) {
                    getChildFragmentManager().beginTransaction()
                            .remove(fragment).commit();
                }
            }
        }

        setupViewPager(savedInstanceSate);
    }

    protected void setupViewPager(Bundle savedInstanceSate) {
        mInnerAdapter = new InnerAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mInnerAdapter);
        if (mCurrentPosition >= mInnerAdapter.getCount())
            mCurrentPosition = 0;
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.addOnPageChangeListener(this);
    }

    protected void destoryFragments() {
        if (getActivity() != null) {

            try {
                FragmentTransaction trs = getActivity().getSupportFragmentManager().beginTransaction();
                Set<String> keySet = fragments.keySet();
                for (String key : keySet) {
                    if (fragments.get(key) != null) {
                        trs.remove(fragments.get(key));

                        Timber.d(TAG, "remove fragment , key = " + key);
                    }
                }
                trs.commit();
            } catch (Throwable e) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;

        if (configLastPositionKey() != null) {
            ActivityHelper.putShareData(App.getInstance(), "PagerLastPosition" + configLastPositionKey(), mItems.get(position).getType());
        }

        // 查看是否需要拉取数据
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof ITabInitData) {
            ((ITabInitData) fragment).onTabRequestData();
        }
    }

    @Override

    public void onPageScrollStateChanged(int position) {

    }

    public String makeFragmentName(int position) {
        return mItems.get(position).getTitle();
    }

    // 是否保留最后阅读的标签
    protected String configLastPositionKey() {
        return null;
    }

    /**
     * 标签页，标签不能重复
     *
     * @return
     */
    abstract protected ArrayList<T> generateTabs();

    abstract protected Fragment newFragment(T bean);

    // 延迟一点初始化tabs，用于在首页切换菜单的时候，太多的tab页导致有点点卡顿
    protected int delayTabInit() {
        return 0;
    }

    @Override
    public void onDestroy() {
//        try {
//            if (!isRetainFragments) {
//                destoryFragments();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        super.onDestroy();
    }

    public Fragment getCurrentFragment() {
        if (mViewPager == null || mInnerAdapter == null || mInnerAdapter.getCount() < mCurrentPosition)
            return null;

        return fragments.get(makeFragmentName(mCurrentPosition));
    }

    public Fragment getFragment(String tabTitle) {
        if (fragments == null || TextUtils.isEmpty(tabTitle))
            return null;

        for (int i = 0; i < mItems.size(); i++) {
            if (tabTitle.equals(mItems.get(i).getTitle())) {
                return fragments.get(makeFragmentName(i));
            }
        }

        return null;
    }

    public Fragment getFragment(int index) {
        if (mItems.size() > index) {
            return fragments.get(makeFragmentName(index));
        }

        return null;
    }

    public Map<String, Fragment> getFragments() {
        return fragments;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    class InnerAdapter extends FragmentPagerAdapter {

        public InnerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragments.get(makeFragmentName(position));
            if (fragment == null) {
                fragment = newFragment(mItems.get(position));

                fragments.put(makeFragmentName(position), fragment);
            }

            return fragment;
        }

//        @Override
//        protected void freshUI(Fragment fragment) {
//        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mItems.get(position).getTitle();
        }

        @Override
        protected String makeFragmentName(int position) {
            return BaseTabsFragment.this.makeFragmentName(position);
        }

    }

    // 这个接口用于多页面时，只有当前的页面才加载数据，其他不显示的页面暂缓加载
    // 当每次onPagerSelected的时候，再调用这个接口初始化数据
    public interface ITabInitData {

        void onTabRequestData();

    }
}
