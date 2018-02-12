package com.nine.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.nine.mvp.R;
import com.nine.mvp.bean.FragmentArgs;

import org.parceler.Parcels;

import java.lang.reflect.Method;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
 */
public class CommonActivity extends SimpleActivity {

    public static final String FRAGMENT_TAG = "FRAGMENT_CONTAINER";

    private int contentId;
    private int overrideTheme = -1;

    /**
     * 启动一个界面
     *
     * @param activity
     * @param clazz
     * @param args
     */
    public static void launch(Activity activity, Class<? extends Fragment> clazz, FragmentArgs args) {
        Intent intent = new Intent(activity, CommonActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtra("args", Parcels.wrap(args));
        activity.startActivity(intent);
    }

//    public static void launchWithTheme(Activity activity, Class<? extends Fragment> clazz, FragmentArgs args, int theme) {
//        Intent intent = new Intent(activity, CommonActivity.class);
//        intent.putExtra("className", clazz.getName());
//        intent.putExtra("overrideTheme", theme);
//        if (args != null)
//            intent.putExtra("args", Parcels.wrap(args));
//        activity.startActivity(intent);
//    }

    public static void launchForResult(Fragment fragment, Class<? extends Fragment> clazz, FragmentArgs args, int requestCode) {
        if (fragment.getActivity() == null)
            return;
        Activity activity = fragment.getActivity();

        Intent intent = new Intent(activity, CommonActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtra("args", Parcels.wrap(args));
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void launchForResult(Activity from, Class<? extends Fragment> clazz, FragmentArgs args, int requestCode) {
        Intent intent = new Intent(from, CommonActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtra("args", Parcels.wrap(args));
        from.startActivityForResult(intent, requestCode);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contentId = savedInstanceState == null ? getLayout()
                : savedInstanceState.getInt("contentId");
        overrideTheme = savedInstanceState == null ? -1
                : savedInstanceState.getInt("overrideTheme");

        SimpleFragment fragment = null;
        if (savedInstanceState == null) {
            try {
                String className = getIntent().getStringExtra("className");
                if (TextUtils.isEmpty(className)) {
                    super.onCreate(savedInstanceState);
                    finish();
                    return;
                }

                FragmentArgs values = (FragmentArgs) getIntent().getParcelableExtra("args");

                Class clazz = Class.forName(className);
                fragment = (SimpleFragment) clazz.newInstance();
                // 设置参数给Fragment
                if (values != null) {
                    try {
                        Method method = clazz.getMethod("setArguments", new Class[]{Bundle.class});
                        method.invoke(fragment, FragmentArgs.transToBundle(values));
                    } catch (Exception e) {
                    }
                }

                // 重写Activity的主题
//                try {
//                    Method method = clazz.getMethod("setActivityTheme");
//                    if (method != null) {
//                        int theme = Integer.parseInt(method.invoke(fragment).toString());
//                        if (theme > 0) {
//                            overrideTheme = theme;
//                        }
//                    }
//                } catch (Exception e) {
//                }
//                // 重写Activity的contentView
//                try {
//                    Method method = clazz.getMethod("inflateActivityContentView");
//                    if (method != null) {
//                        int fragmentConfigId = Integer.parseInt(method.invoke(fragment).toString());
//                        if (fragmentConfigId > 0) {
//                            contentId = fragmentConfigId;
//                        }
//                    }
//                } catch (Exception e) {
//                }
            } catch (Exception e) {
                e.printStackTrace();
                super.onCreate(savedInstanceState);
                finish();
                return;
            }
        }
        super.onCreate(savedInstanceState);
        // 设置主题
        if (overrideTheme > 0)
            setTheme(overrideTheme);
        setContentView(contentId);

        if (fragment != null) {
            if ((fragment).getLayoutId() > 0) {
                loadRootFragment(R.id.fragmentContainer, fragment);
            }
        }

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    @Override
    protected int getLayout() {
        return R.layout.comm_ui_activity;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("contentId", contentId);
        outState.putInt("overrideTheme", overrideTheme);
    }


}
