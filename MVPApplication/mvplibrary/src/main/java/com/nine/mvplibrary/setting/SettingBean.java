package com.nine.mvplibrary.setting;

import org.parceler.Parcel;

@Parcel
class SettingBean  {


	 String description;

	 String type;

	 String value;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
