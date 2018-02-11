package com.nine.mvp.base.adapter;

public interface FragmentPagerChangeListener {

	public void instantiate(String fragmentName);
	
	public void destroy(String fragmentName);
	
}
