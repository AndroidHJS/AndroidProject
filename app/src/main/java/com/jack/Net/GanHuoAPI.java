package com.jack.Net;

import com.jack.bean.GanIO;
import com.jack.bean.MeiZhi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/6.
 */

public interface GanHuoAPI {
    @GET("data/{category}/10/{page}")
    Observable<GanIO> getGankAndroid(@Path("category") String category,  @Path("page") int page);
    @GET("data/福利/{count}/{page}")
    Observable<MeiZhi>  getGankMeizi(@Path("count") int count, @Path("page") int page);
}
