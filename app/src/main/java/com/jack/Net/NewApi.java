package com.jack.Net;

import com.jack.bean.InternetSafe;
import com.jack.bean.New;
import com.jack.bean.NewDetaiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface NewApi {
    @GET("news/latest")
    Observable<New> getNewLatest();
    @GET("news/{id}")
    Observable<NewDetaiBean> getZhihuStory(@Path("id") String id);
    @GET("theme/{id}")
    Observable<InternetSafe>  getThemeStory(@Path("id") String id);


}
