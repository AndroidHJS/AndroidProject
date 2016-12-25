package com.jack.Net;

import com.google.gson.Gson;

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
    public  static NewApi mZhiHuAPI=null;

    /**
     * 得到知乎日报API
     *
     * @return
     */
    static {
        Retrofit GanHuoRetrofit = new Retrofit.Builder().baseUrl(GANK_BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).build();
        mGanHuoAPI=GanHuoRetrofit.create(GanHuoAPI.class);
        Retrofit ZhiHuRetrofit = new Retrofit.Builder().baseUrl(ZHIHU_BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).build();
        mZhiHuAPI=ZhiHuRetrofit.create(NewApi.class);
    }

    /***
     * 获取知乎日报最新资讯
     *
     * @param listener
     * @param mclass
     */
    public static void getNewLatestList(final RequestListener listener, final Class<?> mclass) {
        Call<String> NewCall = mZhiHuAPI.getNewLatest();
        addCall(NewCall,listener,mclass);
    }
    /***
     * 获取新闻的详细信息
     * @param listener
     * @param newId
     */
    public static void getNewDatails(final RequestListener listener, String newId) {
        Call<String> ZhiHuStoryCall = mZhiHuAPI.getZhihuStory(newId);
        addCall(ZhiHuStoryCall,listener,null);
    }
    public static void getGanHuoList(final  RequestListener listener, int count, int page, final Class<?> mclass){
        Call<String> CanHuoCall= mGanHuoAPI.getGankAndroid(count, page);
        addCall(CanHuoCall,listener,mclass);
    }
    public  static  void getInternetSafeList(final  RequestListener listener,String id,final Class<?> mclass){
        Call<String> call=mZhiHuAPI.getThemeStory(id);
        addCall(call,listener,mclass);
    }
    public static void  getMeiZhiList(final  RequestListener listener,int count,int page,final Class<?> mclass){
        Call<String> MeiZhiCall= mGanHuoAPI.getGankMeizi(count,page);
        addCall(MeiZhiCall,listener,mclass);
    }
    private static  void addCall(Call<String> call,final RequestListener listener, final Class<?> mclass){
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Gson gson = new Gson();
                Object obj=null;
                String  responseStr=response.body().toString();
                if(mclass!=null){
                    obj= gson.fromJson(responseStr, mclass);
                }
                if(obj==null){
                    obj=responseStr;
                }
                listener.onSuccess(obj);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail(t);
            }
        });

    }
}




