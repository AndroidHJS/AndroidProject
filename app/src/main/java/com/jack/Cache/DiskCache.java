package com.jack.Cache;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jack.utils.FileUtils;
import com.jack.utils.MD5Utils;
import com.jack.utils.RxJavaUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import rx.Observable;
import rx.Subscriber;
import static com.jack.utils.MD5Utils.hashKeyForDisk;

/**
 * Created by Administrator on 2016/12/25.
 */

public class DiskCache implements ICache {
    private static DiskLruCache  mDiskCache;
    public  static  void init(File directory , int appVersion){
        try {
            mDiskCache=DiskLruCache.open(directory,appVersion,1,10 * 1024 * 1024);
        } catch (IOException e) {
            Log.i("DiskCache","打开硬盘缓存不成功");
        }

    }
    @Override
    public <T> Observable<T> get(final String key, final Class<T> cls) {
      return    Observable.create(new Observable.OnSubscribe<T>(){

            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    Log.i(CacheManager.Tag,"从硬盘获取数据");
                    String md5key = MD5Utils.hashKeyForDisk(key);
                    DiskLruCache.Snapshot snapShot = mDiskCache.get(md5key);
                    if(snapShot==null){
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                        return;
                    }
                   InputStream in = snapShot.getInputStream(0);
                   String result= FileUtils.readString(in);
                   if(TextUtils.isEmpty(result)){
                            subscriber.onNext(null);
                    }else{
                        T t=new Gson().fromJson(result,cls);
                        subscriber.onNext(t);
                    }
                    subscriber.onCompleted();
                    }
                catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

            }
        }).compose(RxJavaUtils.<T>applySchedulers());
    }

    @Override
    public <T> void put(final String key, final T t) {
        Observable.create(new Observable.OnSubscribe<T>(){

            @Override
            public void call(Subscriber<? super T> subscriber) {
                      String md5Key= hashKeyForDisk(key);
             try {
                DiskLruCache.Editor editor = mDiskCache.edit(md5Key);
                 String result=t.toString();
               if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                boolean flag=FileUtils.writeString(result,outputStream);
                if (flag) {
                    editor.commit();
                } else {
                    editor.abort();
                }
                if (!subscriber.isUnsubscribed()) {
                       subscriber.onNext(t);
                       subscriber.onCompleted();
                }
            }
              mDiskCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
               }).compose(RxJavaUtils.<T>applySchedulers()).subscribe();

    }

}
