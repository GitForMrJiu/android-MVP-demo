package com.nine.mvp.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nine.mvp.R;

/**
 * Created by Just For Mr.Jiu on 18/2/9.
 */

public class Fragment_demo extends Fragment{


    public static Fragment_demo newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment_demo fragment = new Fragment_demo();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comm_ui_activity,null);
        return view;
    }
}
