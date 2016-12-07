package com.jack.Net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface NewApi {
    @GET("api/4/news/latest")
    Call<String> getNewLatest();
    @GET("api/4/news/{id}")
    Call<String> getZhihuStory(@Path("id") String id);

}
