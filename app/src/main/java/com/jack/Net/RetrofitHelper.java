package com.jack.Net;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/5.
 */

public class RetrofitHelper {
    private static final String Tag = "RetrofitHelper";
    /**
     * 知乎日报base url
     */
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/";
    /**
     * 干货集中营base url
     */
    public static final String GANK_BASE_URL = "http://gank.io/api/";
    public  static GanHuoAPI mGanHuoAPI=null;

    /**
     * 得到知乎日报API
     *
     * @return
     */
    static {
        Retrofit GanHuoRetrofit = new Retrofit.Builder().baseUrl(GANK_BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).build();
        mGanHuoAPI=GanHuoRetrofit.create(GanHuoAPI.class);
    }
    private static NewApi getZhiHuAPI() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ZHIHU_BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).build();
        return retrofit.create(NewApi.class);
    }

    /***
     * 获取知乎日报最新资讯
     *
     * @param listener
     * @param mclass
     */
    public static void getNewLatestList(final RequestListener listener, final Class<?> mclass) {
        NewApi newApi = getZhiHuAPI();
        Call<String> NewCall = newApi.getNewLatest();
        NewCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Gson gson = new Gson();
                Object o = gson.fromJson(response.body().toString(), mclass);
                listener.onSuccess(o);


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail(t);
            }
        });


    }

    /***
     * 获取新闻的详细信息
     * @param listener
     * @param newId
     */
    public static void getNewDatails(final RequestListener listener, String newId) {
        Call<String> zhihuStoryCall = getZhiHuAPI().getZhihuStory(newId);
        zhihuStoryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                 listener.onFail(t);
            }
        });

    }

    public static void getGanHuoList(final  RequestListener listener, int count, int page, final Class<?> mclass){
        Call<String> gankCall= mGanHuoAPI.getGankAndroid(count, page);
        gankCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Gson gson = new Gson();
                Object o = gson.fromJson(response.body().toString(), mclass);
                listener.onSuccess(o);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public static void  getMeiZhiList(final  RequestListener listener,int count,int page,final Class<?> mclass){
        Call<String> gankCall= mGanHuoAPI.getGankMeizi(count,page);
        gankCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Gson gson = new Gson();
                Object o = gson.fromJson(response.body().toString(), mclass);
                listener.onSuccess(o);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}




