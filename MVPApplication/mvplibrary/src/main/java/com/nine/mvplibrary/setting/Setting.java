package com.nine.mvplibrary.setting;

import com.google.gson.Gson;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

@Parcel
public class Setting extends SettingBean  {


	 Map<String, SettingExtra> extras;

	public Setting() {
		extras = new HashMap<String, SettingExtra>();
	}

	public Map<String, SettingExtra> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, SettingExtra> extras) {
		this.extras = extras;
	}

	public Setting copy() {

		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(this),Setting.class);
	}

}
