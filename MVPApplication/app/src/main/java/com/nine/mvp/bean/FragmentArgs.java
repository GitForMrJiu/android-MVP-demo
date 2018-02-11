package com.nine.mvp.bean;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Just For Mr.Jiu on 18/2/5.
 */
@Parcel
public class FragmentArgs {

     Map<String, Parcelable> values = new HashMap<String, Parcelable>();

    public FragmentArgs add(String key, Parcelable value) {
        if (!TextUtils.isEmpty(key) && value != null)
            values.put(key, value);
        return this;
    }

    public Parcelable get(String key) {
        return values.get(key);
    }

    public static void setToBundle(Bundle bundle, FragmentArgs args) {
        Set<String> keys = args.values.keySet();
        for (String key : keys) {
            Parcelable value = args.get(key);
            if (value == null)
                continue;
            bundle.putParcelable(key, value);
        }
    }

    public static FragmentArgs transToArgs(Bundle bundle) {
        FragmentArgs args = new FragmentArgs();
        for (String s : bundle.keySet()) {
            Object o = bundle.get(s);
            if (o == null) continue;
            args.add(s, (Parcelable) o);
        }
        return args;
    }

    public static Bundle transToBundle(FragmentArgs args) {
        Bundle bundle = new Bundle();
        setToBundle(bundle, args);
        return bundle;
    }

}
