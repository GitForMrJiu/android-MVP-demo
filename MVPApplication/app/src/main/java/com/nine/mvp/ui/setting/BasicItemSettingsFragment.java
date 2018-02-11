package com.nine.mvp.ui.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.nine.mvp.R;
import com.nine.mvp.app.App;
import com.nine.mvplibrary.setting.AppSettings;
import com.nine.mvplibrary.setting.SettingUtility;
import com.nine.mvplibrary.utils.ActivityHelper;

/**
 * Created by thinkformoney on 16/8/19.
 */
public class BasicItemSettingsFragment extends BasePreferenceFragment
        implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    public static BasicItemSettingsFragment newInstance() {

        Bundle args = new Bundle();

        BasicItemSettingsFragment fragment = new BasicItemSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Preference pTheme;// 主题设置

    //    private ListPreference pTextSize;// 字体大小
//    private CheckBoxPreference pShowRemark;// 显示备注
    private CheckBoxPreference pShowDefGroup;// 显示默认分组微博

    private CheckBoxPreference pFastScrollBar;// 显示快速滚动条
    private Preference pPicSavePath;// 图片保存路径
    private Preference pClearRecentMention;// 清理@历史记录

    private CheckBoxPreference pAutoRefresh;// 列表自动刷新
    private ListPreference pSwipebackEdgeMode;// 手势返回方向
    //    private ListPreference pFabType;// 首页fab按钮功能
    private ListPreference pFabPosition;// 首页fab按钮位置
    private CheckBoxPreference pInnerBrowser;// 设置默认浏览器
    private CheckBoxPreference pRotatePic;// 设置旋转照片
    private CheckBoxPreference pSendDelay;// 内容发布延迟

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addPreferencesFromResource(R.xml.ui_basic_settings_item);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        int value = 0;

        // 主题
        pTheme = (Preference) findPreference("pTheme");
        pTheme.setOnPreferenceClickListener(this);
        pTheme.setSummary(getResources().getStringArray(R.array.mdColorNames)[AppSettings.getThemeColor()]);

        pPicSavePath = (Preference) findPreference("pPicSavePath");
        pPicSavePath.setOnPreferenceClickListener(this);
//		pPicSavePath.setSummary(SystemUtility.getSdcardPath() + File.separator + AppSettings.getImageSavePath() + File.separator);
//        pPicSavePath.setSummary("/sdcard" + File.separator + AppSettings.getImageSavePath() + File.separator);


//        pTextSize = (ListPreference) findPreference("pTextSize");
//        pTextSize.setOnPreferenceChangeListener(this);
        value = Integer.parseInt(prefs.getString("pTextSize", "4"));
//        setTextSize(value);

//        pShowRemark = (CheckBoxPreference) findPreference("pShowRemark");
//        pShowRemark.setOnPreferenceChangeListener(this);

        pShowDefGroup = (CheckBoxPreference) findPreference("pShowDefGroup");
        if (pShowDefGroup != null)
            pShowDefGroup.setOnPreferenceChangeListener(this);

//        CheckBoxPreference pDoubleClickToRefresh = (CheckBoxPreference) findPreference("pDoubleClickToRefresh");
//		pDoubleClickToRefresh.setOnPreferenceChangeListener(this);

//        pAutoRefresh = (CheckBoxPreference) findPreference("pAutoRefresh");
//        pAutoRefresh.setOnPreferenceChangeListener(this);
//        pAutoRefresh.setChecked(SettingUtility.getPermanentSettingAsBool("pAutoRefresh", true));

//        pSwipebackEdgeMode = (ListPreference) findPreference("pSwipebackEdgeMode");
//        pSwipebackEdgeMode.setOnPreferenceChangeListener(this);
//        value = Integer.parseInt(prefs.getString("pSwipebackEdgeMode", "0"));
//        setListSetting(value, R.array.swipebackEdgeMode, pSwipebackEdgeMode);


        // 缓存清理
        Preference pClearCache = (Preference) findPreference("pClearCache");
//        CacheClearFragment clearFragment = (CacheClearFragment) getActivity().getSupportFragmentManager().findFragmentByTag("CacheClearFragment");
//        if (clearFragment == null) {
//            clearFragment = new CacheClearFragment();
//            getActivity().getSupportFragmentManager().beginTransaction().add(clearFragment, "CacheClearFragment").commit();
//        }
//        clearFragment.setPreference(pClearCache, AppContext.getImagePath());

//        pRotatePic = (CheckBoxPreference) findPreference("pRotatePic");
//        pRotatePic.setOnPreferenceChangeListener(this);

        pInnerBrowser = (CheckBoxPreference) findPreference("pInnerBrowser");
        pInnerBrowser.setOnPreferenceChangeListener(this);
        pInnerBrowser.setChecked(AppSettings.isInnerBrower());
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // 设置图片保存路径
        if ("pPicSavePath".equals(preference.getKey())) {
            modifyImageSavePath();
        }
        // MD Colors
        else if ("pTheme".equals(preference.getKey())) {
//			ThemeStyleSettingsFragment.launch(getActivity());
            MDColorsDialogFragment.launch(getActivity());

        }
        // 自定义颜色
        else if ("pThemeCustom".equals(preference.getKey())) {
//            CustomThemeColorFragment.launch(getActivity());
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // 是否显示默认分组
        if ("pShowDefGroup".equals(preference.getKey())) {
            ActivityHelper.putBooleanShareData(App.getInstance(), "ChanneSortHasChanged", true);
        }
        // 列表字体
        else if ("pTextSize".equals(preference.getKey())) {
//            setTextSize(Integer.parseInt(newValue.toString()));
        }
        // 列表控件是否自动刷新
        else if ("pAutoRefresh".equals(preference.getKey())) {
            SettingUtility.setPermanentSetting("pAutoRefresh", Boolean.parseBoolean(newValue.toString()));
        } else if ("pSwipebackEdgeMode".equals(preference.getKey())) {
//            setListSetting(Integer.parseInt(newValue.toString()), R.array.swipebackEdgeMode, pSwipebackEdgeMode);

//            mHandler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    BaseActivityHelper activityHelper = (BaseActivityHelper) ((BaseActivity) getActivity()).getActivityHelper();
//                    activityHelper.setSwipebackEdgeMode();
//                }
//
//            }, 500);
        } else if ("pInnerBrowser".equals(preference.getKey())) {
            if ("pInnerBrowser".equals(preference.getKey())) {
                ActivityHelper.putBooleanShareData(App.getInstance(), "pInnerBrowser", true);
            }
//			AppSettings
//			try {
//				AisenTextView.stringMemoryCache.evictAll();
//			} catch (Exception e) {
//			}
        }
        return true;
    }

    // 修改图片保存路径
    private void modifyImageSavePath() {
//        View entryView = View.inflate(getActivity(), R.layout.lay_dialog_remark_entry, null);
//        final EditText editRemark = (EditText) entryView.findViewById(R.id.editRemark);
//        editRemark.setHint(R.string.settings_dir_hint);
//        editRemark.setText(AppSettings.getImageSavePath());
//        editRemark.setSelection(editRemark.getText().toString().length());
//        new AlertDialogWrapper.Builder(getActivity()).setTitle(R.string.settings_modify_picpath_title)
//                .setView(entryView)
//                .setNegativeButton(R.string.cancel, null)
//                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (TextUtils.isEmpty(editRemark.getText().toString())) {
//                            ViewUtils.showMessage(getActivity(), R.string.update_faild);
//                            return;
//                        }
//
//                        String path = SystemUtils.getSdcardPath() + File.separator + editRemark.getText().toString() + File.separator;
//                        File file = new File(path);
//                        if (file.exists() || file.mkdirs()) {
//                            AppSettings.setImageSavePath(editRemark.getText().toString());
//
////										pPicSavePath.setSummary(path);
//                            pPicSavePath.setSummary("/sdcard" + File.separator + editRemark.getText().toString() + File.separator);
//
//                            ViewUtils.showMessage(getActivity(), R.string.update_success);
//                        }
//                        else {
//                            ViewUtils.showMessage(getActivity(), R.string.update_faild);
//                        }
//                    }
//
//                })
//                .show();
    }

//    private void setTextSize(int value) {
//        String[] valueTitleArr = getResources().getStringArray(R.array.txtSizeNum);
//
//        pTextSize.setSummary(valueTitleArr[value]);
//    }

    Handler mHandler = new Handler();

}
