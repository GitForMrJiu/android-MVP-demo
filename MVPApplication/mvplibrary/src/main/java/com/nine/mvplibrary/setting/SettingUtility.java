package com.nine.mvplibrary.setting;

import android.content.Context;

import com.nine.mvplibrary.DHApplication;
import com.nine.mvplibrary.utils.ActivityHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SettingUtility {

	private static Map<String, Setting> settingMap;

	static {
		settingMap = new HashMap<String, Setting>();
	}

	private SettingUtility() {

	}

	/**
	 * 添加设置配置数据
	 * 
	 * @param settingsXmlName
	 */
	public static void addSettings(Context context, String settingsXmlName) {
		Map<String, Setting> newSettingMap = SettingsXmlParser.parseSettings(context, settingsXmlName);

		Set<String> keySet = newSettingMap.keySet();
		for (String key : keySet)
			settingMap.put(key, newSettingMap.get(key));
	}

	public static boolean getBooleanSetting(String type) {
		if (settingMap.containsKey(type))
			return Boolean.parseBoolean(settingMap.get(type).getValue());

		return false;
	}

	public static int getIntSetting(String type) {
		if (settingMap.containsKey(type))
			return Integer.parseInt(settingMap.get(type).getValue());

		return -1;
	}

	public static String getStringSetting(String type) {
		if (settingMap.containsKey(type))
			return settingMap.get(type).getValue();

		return null;
	}

	public static Setting getSetting(String type) {
		if (settingMap.containsKey(type))
			return settingMap.get(type);

		return null;
	}

	public static void setPermanentSetting(String type, boolean value) {
		ActivityHelper.putBooleanShareData(DHApplication.getInstance(), type, value);
	}

	public static boolean getPermanentSettingAsBool(String type, boolean def) {
		return ActivityHelper.getBooleanShareData(DHApplication.getInstance(), type,
				settingMap.containsKey(type) ? Boolean.parseBoolean(settingMap.get(type).getValue()) : def);
	}

	public static void setPermanentSetting(String type, int value) {
		ActivityHelper.putIntShareData(DHApplication.getInstance(), type, value);
	}

	public static int getPermanentSettingAsInt(String type) {
		return ActivityHelper.getIntShareData(DHApplication.getInstance(), type,
				settingMap.containsKey(type) ? Integer.parseInt(settingMap.get(type).getValue()) : -1);
	}

	public static void setPermanentSetting(String type, String value) {
		ActivityHelper.putShareData(DHApplication.getInstance(), type, value);
	}

	public static String getPermanentSettingAsStr(String type, String def) {
		return ActivityHelper.getShareData(DHApplication.getInstance(), type, settingMap.containsKey(type) ? settingMap.get(type).getValue() : def);
	}

}
