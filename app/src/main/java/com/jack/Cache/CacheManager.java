package com.jack.Cache;

import android.util.Log;

import com.jack.bean.BaseBean;
import com.jack.utils.ToastUtils;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CacheManager {
    public static final String Tag = "CacheManager";
    private  static CacheManager mInstance=null;
    private  DiskCache mDiskCache;
    private MemoryCache mMemoryCache;
    private CacheManager(){
        mDiskCache=new DiskCache();
        mMemoryCache=new MemoryCache();

    }
    public  static  CacheManager getInstance(){
        if(mInstance==null){
            synchronized (CacheManager.class){
                mInstance=new CacheManager();
            }
        }
        return  mInstance;
    }
    public  <T extends BaseBean> Observable<T> loadData(String key, Class<T> cls, NetWorkCache<T> networkCache){
        Observable<T> MemoryObservable = LoadMemoryData(key, cls);
        Observable<T> DiskObservable = LoadDiskData(key, cls);
        Observable<T> NetObservable = LoadNetData(key, cls, networkCache);
        Observable<T> observable =  Observable.concat(MemoryObservable,DiskObservable,NetObservable).first(new Func1<T, Boolean>() {
            @Override
            public Boolean call(T t) {
                return t!=null&&!t.isExpire() ;
            }
        });

        return observable;

    }
    private   <T> Observable<T> LoadDiskData(final String key, Class<T> cls){
        return  mDiskCache.get(key,cls).doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                 if(t!=null){
                     Log.i(Tag,"存放数据到内存中");
                     mMemoryCache.put(key,t);
                 }
            }
        });

    }
    private  <T> Observable<T>  LoadMemoryData(String key, Class<T> cls){
        return  mMemoryCache.get(key,cls);
    }
    private   <T> Observable<T> LoadNetData(final String key, Class<T> cls, NetWorkCache<T> networkCache){

       return     networkCache.get(key,cls).doOnNext(new Action1<T>() {
           @Override
           public void call(T t) {
               if(t!=null){
                   ToastUtils.showToast("从网上获取数据");
                   Log.i(Tag,"存放数据到内存 和硬盘");
                   mMemoryCache.put(key,t);
                   mDiskCache.put(key,t);
               }
           }
       });
    }

}
