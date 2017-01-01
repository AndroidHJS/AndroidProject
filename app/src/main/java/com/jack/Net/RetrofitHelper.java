package com.jack.Net;

import com.jack.Cache.CacheManager;
import com.jack.Cache.NetWorkCache;
import com.jack.bean.BaseBean;
import com.jack.bean.GanIO;
import com.jack.bean.InternetSafe;
import com.jack.bean.MeiZhi;
import com.jack.bean.New;
import com.jack.bean.NewDetaiBean;
import com.jack.global.Constant;
import com.jack.main.BuildConfig;
import com.jack.utils.RxJavaUtils;
import com.jack.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/5.
 */

public class RetrofitHelper {
    private static final String Tag = "RetrofitHelper";
    public  static GanHuoAPI mGanHuoAPI=null;
    public  static NewApi mZhiHuAPI=null;
    private static final int TIME_OUT_LIMIT = 30;
    private static RxJavaCallAdapterFactory mAdapterFactory;
    private static  GsonConverterFactory mGsonConverterFactory;
    private  static  OkHttpClient mOkHttpClient;;
    /**
     * 得到知乎日报API
     *
     * @return
     */
    static {
        //设置超时时间
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .build();
                mGsonConverterFactory = GsonConverterFactory.create();
        mAdapterFactory = RxJavaCallAdapterFactory.create();
        Retrofit GanHuoRetrofit = new Retrofit.Builder().baseUrl(BuildConfig.GanHuoBaseUrl)
                .addCallAdapterFactory(mAdapterFactory)
                .addConverterFactory(mGsonConverterFactory)
                .client(mOkHttpClient)
                .build();
        mGanHuoAPI=GanHuoRetrofit.create(GanHuoAPI.class);
        Retrofit ZhiHuRetrofit = new Retrofit.Builder().baseUrl(BuildConfig.ZhiHuBaseUrl)
                .addCallAdapterFactory(mAdapterFactory)
                .client(mOkHttpClient)
                .addConverterFactory(mGsonConverterFactory).build();
        mZhiHuAPI=ZhiHuRetrofit.create(NewApi.class);
    }

    /***
     * 获取知乎日报最新资讯
     *
     */
    public static  Observable<New> getNewLatestList() {
          NetWorkCache<New> netCache=new NetWorkCache<New>() {
            @Override
            public Observable<New> get(String key, Class<New> cls) {
                return mZhiHuAPI.getNewLatest().compose(RxJavaUtils.<New>applySchedulers());
            }
          };
        return  LoadData(Constant.CacheKey.NewKey,New.class,netCache);
    }
    public  static  Observable<InternetSafe> getInternetSafeList(final String id){
        NetWorkCache<InternetSafe>  safeCache=new NetWorkCache<InternetSafe>() {
            @Override
            public Observable<InternetSafe> get(String key, Class<InternetSafe> cls) {
                return mZhiHuAPI.getThemeStory(id).compose(RxJavaUtils.<InternetSafe>applySchedulers());
            }
        };
        String key=String.format(Constant.CacheKey.SafeKey,id);
        return LoadData(key,InternetSafe.class,safeCache);
    }
    /***
     * 获取新闻的详细信息
     * @param newId
     */
    public static  Observable<NewDetaiBean> getNewDatails(final String newId) {
        NetWorkCache<NewDetaiBean> newDatailsCache=new NetWorkCache<NewDetaiBean>() {
            @Override
            public Observable<NewDetaiBean> get(String key, Class<NewDetaiBean> cls) {
                return mZhiHuAPI.getZhihuStory(newId).compose(RxJavaUtils.<NewDetaiBean>applySchedulers());
            }
        };
        String key=String.format(Constant.CacheKey.NewDatailsKey,newId);
        return LoadData(key,NewDetaiBean.class,newDatailsCache);

    }
    public static Observable<GanIO>  getGanHuoList(final String category, final int page){
        NetWorkCache<GanIO> ganHuoCache=new NetWorkCache<GanIO>() {
            @Override
            public Observable<GanIO> get(String key, Class<GanIO> cls) {
                return mGanHuoAPI.getGankAndroid(category,page).compose(RxJavaUtils.<GanIO>applySchedulers());
            }
        };
        String key=String.format(Constant.CacheKey.GanHuoKey,category,page);
        return LoadData(key,GanIO.class,ganHuoCache);

    }
    private static  <T extends BaseBean> Observable<T> LoadData(String key, Class<T> cls, NetWorkCache<T> netCache){
        Observable<T> Observable =CacheManager
                .getInstance()
                .loadData(key, cls, netCache)
                .compose(RxJavaUtils.<T>applySchedulers());
        return  Observable;
    }
    public static Observable<MeiZhi>  getMeiZhiList(final int count,final  int page){
        NetWorkCache<MeiZhi> ganHuoCache=new NetWorkCache<MeiZhi>() {
            @Override
            public Observable<MeiZhi> get(String key, Class<MeiZhi> cls) {
                return mGanHuoAPI.getGankMeizi(count, page).compose(RxJavaUtils.<MeiZhi>applySchedulers());
            }
        };
        String key=String.format(Constant.CacheKey.MeiZhiKey,count,page);
        return  LoadData(key,MeiZhi.class,ganHuoCache);

    }

}




