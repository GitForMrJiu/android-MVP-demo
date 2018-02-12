package com.nine.mvp.model.http;

import com.nine.mvp.model.http.apis.MyApis;

import javax.inject.Inject;

/**
 * Created by Just For Mr.Jiu on 18/2/12.
 * Email yufan595@gmail.com
 */

public class RetrofitHelper implements HttpHelper {

    private MyApis mMyApiService;

    @Inject
    public RetrofitHelper(MyApis myApiService)
    {
        this.mMyApiService = myApiService;
    }
}
