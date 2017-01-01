package com.jack;

import android.app.Application;
import android.content.Context;

import com.jack.Cache.DiskCache;
import com.jack.Cache.MemoryCache;
import com.jack.utils.FileUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/12/28.
 */

public class AppApplication extends Application{
    File  cacheFile=null;
    private  static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        cacheFile= FileUtils.getDiskCacheDir(this,"data");
        DiskCache.init(cacheFile,1);
        MemoryCache.init();
    }
    public static Context getAppApplicationContext(){
        return  mContext;
    }
}
