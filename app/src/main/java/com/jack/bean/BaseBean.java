package com.jack.bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BaseBean {
    private  long mCreatTime=System.currentTimeMillis();
    //过期时间
    private static final long EXPIRE_LIMIT =  5*60* 1000;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    public  Boolean isExpire(){
        return  System.currentTimeMillis()-mCreatTime>=EXPIRE_LIMIT;
    }
}
