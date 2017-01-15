package com.jack.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.jack.AppApplication;
import com.jack.login.Base.User;

/**
 * Created by jack on 2017/1/15.
 */

public class UserInfo {
    private  static  UserInfo mUserInfo;
    private Context mContext;
    private SharedPreferences mUserSp;
    private  UserInfo(){
        mContext= AppApplication.getAppApplicationContext();
        mUserSp = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
    public  static  UserInfo getInstance(){
        if(mUserInfo==null){
            synchronized (UserInfo.class){
                mUserInfo=new UserInfo();
                return mUserInfo;
            }
        }
        return mUserInfo;
    }
    public    void  saveUser(User user){
        SharedPreferences.Editor editor = mUserSp.edit();
        editor.putString("name",user.getNickname());
        editor.putString("HeadImageUrl",user.getHeadImageUrl());
        editor.putString("OpenId",user.getOpenId());
        editor.putInt("sex",user.getSex());
        editor.putBoolean("isLogin",true);
        editor.commit();
    }
    public    User  readUser(){
        User user=new User();
        user.setNickname(mUserSp.getString("name",""));
        user.setHeadImageUrl(mUserSp.getString("HeadImageUrl",""));
        user.setOpenId(mUserSp.getString("OpenId",""));
        user.setSex(mUserSp.getInt("sex",3));
        return  user;
    }
    public  boolean isLogin(){
        return mUserSp.getBoolean("isLogin",false);
    }
}
