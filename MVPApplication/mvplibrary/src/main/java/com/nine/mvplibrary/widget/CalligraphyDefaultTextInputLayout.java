package com.nine.mvplibrary.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Created by Just For Mr.Jiu on 18/2/1.
 */

public class CalligraphyDefaultTextInputLayout extends TextInputLayout {

    Typeface calligraphyTypeface;

    public CalligraphyDefaultTextInputLayout(Context context) {
        super(context);
        initCalligraphyTypeface();
    }

    public CalligraphyDefaultTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCalligraphyTypeface();
    }

    public CalligraphyDefaultTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCalligraphyTypeface();
    }

    @Override
    public void addView(View child, int index, final ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child instanceof EditText) {
            if (calligraphyTypeface != null) {
                setTypeface(calligraphyTypeface);
            }
        }
    }

    private void initCalligraphyTypeface() {
        String fontPath = CalligraphyConfig.get().getFontPath();
        if(fontPath != null) {
            calligraphyTypeface = TypefaceUtils.load(getResources().getAssets(), fontPath);
        }
    }
}
