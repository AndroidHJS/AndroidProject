package com.jack.Cache;

import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.google.gson.Gson;
import com.jack.utils.MD5Utils;
import com.jack.utils.RxJavaUtils;

import java.io.UnsupportedEncodingException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MemoryCache  implements  ICache{
   private  static LruCache<String,String> mMemoryCache;
    public  static void init(){
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mMemoryCache = new LruCache<String, String>(mCacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("UTF-8").length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return value.getBytes().length;
                }
            }
        };
    }

    @Override
    public <T> Observable<T> get(final String key, final Class<T> cls) {
        return  Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Log.i(CacheManager.Tag,"从内存中获取数据");
                try{
                    String md5Key= MD5Utils.hashKeyForDisk(key);
                    String result = mMemoryCache.get(md5Key);
                    if(result!=null&&!TextUtils.isEmpty(result)){
                        T obj = new Gson().fromJson(result, cls);
                        subscriber.onNext(obj);
                    }else{
                        subscriber.onNext(null);
                    }

                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }

            }
        }).compose(RxJavaUtils.<T>applySchedulers());
    }

    @Override
    public <T> void put(String key, T t) {
             String md5Key=MD5Utils.hashKeyForDisk(key);
             String jsonStr=t.toString();
             mMemoryCache.put(md5Key,jsonStr);

    }
}
