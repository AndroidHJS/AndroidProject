package com.jack.Net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/12/6.
 */

public interface GanHuoAPI {
    @GET("data/Android/{count}/{page}")
    Call<String> getGankAndroid(@Path("count") int count, @Path("page") int page);
    @GET("data/福利/{count}/{page}")
    Call<String> getGankMeizi(@Path("count") int count, @Path("page") int page);
}
