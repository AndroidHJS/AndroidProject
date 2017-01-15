package com.jack;

import android.app.Application;
import android.content.Context;

import com.jack.Cache.DiskCache;
import com.jack.Cache.MemoryCache;
import com.jack.global.Constant;
import com.jack.utils.FileUtils;
import com.tencent.tauth.Tencent;

import java.io.File;

/**
 * Created by Administrator on 2016/12/28.
 */

public class AppApplication extends Application{
    File  cacheFile=null;
    private  static Context mContext;
    private Tencent mTencent;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        mTencent= Tencent.createInstance(Constant.AppKey.QQ_app_key,this);
        cacheFile= FileUtils.getDiskCacheDir(this,"data");
        DiskCache.init(cacheFile,1);
        MemoryCache.init();
    }

//    public Tencent getmTencent() {
//        return mTencent;
//    }

    public static Context getAppApplicationContext(){
        return  mContext;
    }
}
