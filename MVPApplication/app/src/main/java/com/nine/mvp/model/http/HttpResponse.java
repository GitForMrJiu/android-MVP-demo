package com.nine.mvp.model.http;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
 */

public class HttpResponse<T> {

    @SerializedName("data")
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @SerializedName("ret")
    private String code;
    private String msg;

    public boolean isDone()
    {
        if (!TextUtils.isEmpty(code)&&code.equals("1"))
        {
            return true;
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
