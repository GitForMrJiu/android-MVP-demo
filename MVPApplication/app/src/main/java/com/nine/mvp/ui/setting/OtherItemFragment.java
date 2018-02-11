package com.nine.mvp.ui.setting;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.nine.mvp.R;
import com.nine.mvp.app.App;
import com.nine.mvplibrary.setting.SettingUtility;


/**
 * 其他
 * 
 * @author wangdan
 *
 */
public class OtherItemFragment extends VersionSettingsFragment 
										implements OnPreferenceClickListener, OnPreferenceChangeListener {

	public static OtherItemFragment newInstance() {
		
		Bundle args = new Bundle();
		
		OtherItemFragment fragment = new OtherItemFragment();
		fragment.setArguments(args);
		return fragment;
	}
	private Preference pAppFeedback;// 用户反馈
	private Preference pAbout;
	private Preference pFeedback;
	private Preference pUpdate;
	private Preference pOpensource;// 开源协议
	private Preference pGithub;// Github
	private CheckBoxPreference pScreenRotate;// 屏幕旋转
	private CheckBoxPreference pDisableCache;// 禁用缓存
	private CheckBoxPreference pCrashLog;// Crash日志上报
	private CheckBoxPreference pNetworkDelay;// 网络请求延迟
	private ListPreference pCacheValidity;// 业务数据有效期

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		addPreferencesFromResource(R.xml.ui_about_item);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
		
		pFeedback = (Preference) findPreference("pFeedback");
		if (pFeedback != null)
			pFeedback.setOnPreferenceClickListener(this);
		
//		pAbout = (Preference) findPreference("pAbout");
//		pAbout.setOnPreferenceClickListener(this);

//        Preference pHelp = (Preference) findPreference("pHelp");
//		pHelp.setOnPreferenceClickListener(this);
		
//		pScreenRotate = (CheckBoxPreference) findPreference("pScreenRotate");
//		pScreenRotate.setOnPreferenceChangeListener(this);
//
//		pDisableCache = (CheckBoxPreference) findPreference("pDisableCache");
//		pDisableCache.setOnPreferenceChangeListener(this);

//		pCrashLog = (CheckBoxPreference) findPreference("pCrashLog");
//		pCrashLog.setOnPreferenceChangeListener(this);

//		pNetworkDelay = (CheckBoxPreference) findPreference("pNetworkDelay");
//		pNetworkDelay.setOnPreferenceChangeListener(this);
		

		// 缓存有效期
//		pCacheValidity = (ListPreference) findPreference("pCacheValidity");
//		pCacheValidity.setOnPreferenceChangeListener(this);
//		pCacheValidity.setEnabled(!AppSettings.isDisableCache());
//		int value = Integer.parseInt(prefs.getString("pCacheValidity", "1"));
//		setListSetting(value, R.array.pCacheValidity, pCacheValidity);
		
//		pAppFeedback = (Preference) findPreference("pAppFeedback");
//		pAppFeedback.setOnPreferenceClickListener(this);
		pUpdate = (Preference) findPreference("pUpdate");
//		pUpdate.setOnPreferenceClickListener(this);

	}
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if ("pScreenRotate".equals(preference.getKey())) {
			if (Boolean.parseBoolean(newValue.toString()))
				getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
			else 
				getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		else if ("pDisableCache".equals(preference.getKey())) {
			pCacheValidity.setEnabled(!Boolean.parseBoolean(newValue.toString()));
		}
		else if ("pCacheValidity".equals(preference.getKey())) {
			setListSetting(Integer.parseInt(newValue.toString()), R.array.pCacheValidity, pCacheValidity);
		}
		else if ("pNetworkDelay".equals(preference.getKey())) {
			SettingUtility.setPermanentSetting("http_delay", Boolean.parseBoolean(newValue.toString()) ? 10 * 1000 : 0);
		}
		else if ("pCrashLog".equals(preference.getKey())) {
			if (Boolean.parseBoolean(newValue.toString())) {
//				checkPhotoPermission(((BaseActivity) getActivity()), false);
			}
		}
		return true;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if ("pFeedback".equals(preference.getKey())) {
//			PublishActivity.publishFeedback(getActivity());
			Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
		}
		else if ("pAbout".equals(preference.getKey())) {
//			AboutWebFragment.launchAbout(getActivity());
//			if (AppSettings.isInnerBrower())
//			{
//				Intent intent = new Intent(getActivity(), BrowserActivity.class);
//				intent.setAction(Intent.ACTION_VIEW);
//				intent.putExtra("title", "关于");
//				intent.putExtra("url", "http://117.41.186.196:8026/09BigDataAnalysis/PropagandaAnalysis/PropagAna.html");
//				getActivity().startActivity(intent);
//			}else
//			{
//				Utils.launchBrowser(getActivity(),"http://117.41.186.196:8026/09BigDataAnalysis/PropagandaAnalysis/PropagAna.html");
//			}

		}
		else if ("pHelp".equals(preference.getKey())) {
//			AboutWebFragment.launchHelp(getActivity());
		}
		else if ("pAppFeedback".equals(preference.getKey())) {
//			PublishActivity.publishFeedback(getActivity());
		}
		else if ("pUpdate".equals(preference.getKey())) {
//			new UpdateManager(getActivity(), true, null).checkVersion(UpdateManager.UpdateMode.ForeGround, false);
		}
		return super.onPreferenceClick(preference);
	}
	

}
