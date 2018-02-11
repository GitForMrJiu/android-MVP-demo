package com.nine.mvp.bean;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * ViewPager的Tab页标签
 * Created by Just For Mr.Jiu on 18/2/2.
 */
@Parcel
public class TabItem {


    public String type;

    public String title;

    public Serializable tag;

    public TabItem() {

    }

    public TabItem(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Serializable getTag() {
        return tag;
    }

    public void setTag(Serializable tag) {
        this.tag = tag;
    }
}
