package com.nine.mvp.model.http;

import com.nine.mvp.model.http.apis.MyApis;

import javax.inject.Inject;

/**
 * Created by thinkformoney on 18/1/19.
 */

public class RetrofitHelper implements HttpHelper {

    private MyApis mMyApiService;

    @Inject
    public RetrofitHelper(MyApis myApiService)
    {
        this.mMyApiService = myApiService;
    }
}
