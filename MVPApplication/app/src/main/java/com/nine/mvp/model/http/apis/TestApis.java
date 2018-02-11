package com.nine.mvp.model.http.apis;

import com.nine.mvp.bean.User;
import com.nine.mvp.model.http.HttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by thinkformoney on 18/1/19.
 */

public interface TestApis {

    String HOST = "http://www.mocky.io/v2/";

    @POST("588d15d3100000ae072d2944")
    Flowable<HttpResponse<User>> getMainModel(@Query("type") String name, @Query("type") String password);

}
