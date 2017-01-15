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
     interface   AppKey{
         String wei_bo_app_key="3514363928";
         String QQ_app_key ="1105855085";
         String QQ_app_app_="97xUAcQbLajRBQlK";
         String wechat_app_key="wx33dc73d316047c84";
     }
    interface  LoginPlatform{
        int QQ = 431;
        int WeiBo=223;
        int WeChat=224;
    }

}
