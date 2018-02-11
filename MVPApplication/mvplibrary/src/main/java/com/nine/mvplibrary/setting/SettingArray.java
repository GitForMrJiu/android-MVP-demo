package com.nine.mvplibrary.setting;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class SettingArray extends SettingBean  {


	 List<Setting> settingArray;

	 int index;
	
	public SettingArray() {
		settingArray = new ArrayList<Setting>();
	}

	public List<Setting> getSettingArray() {
		return settingArray;
	}

	public void setSettingArray(List<Setting> settingArray) {
		this.settingArray = settingArray;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
