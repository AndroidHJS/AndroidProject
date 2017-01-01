package com.jack.global;

import com.jack.main.BuildConfig;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface Constant {
     interface   CacheKey{
         String NewKey= BuildConfig.ZhiHuBaseUrl+"news/latest";
         String SafeKey=BuildConfig.ZhiHuBaseUrl+"theme/%s";
         String NewDatailsKey=BuildConfig.ZhiHuBaseUrl+"news/%s";
         String  GanHuoKey=BuildConfig.GanHuoBaseUrl+"data/%s/%s";
         String MeiZhiKey=BuildConfig.GanHuoBaseUrl+"data/福利/%s/%s";
    }
     interface   ZhiHuCategory{
         String InternetSaFe="10";
         String  Music="7";
         String Sports="8";
     }
}
