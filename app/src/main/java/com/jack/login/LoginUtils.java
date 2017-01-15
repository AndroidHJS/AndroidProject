package com.jack.login;

import android.app.Activity;
import android.util.SparseArray;

import com.jack.global.Constant;
import com.jack.login.Base.LoginInstance;
import com.jack.login.Base.LoginListener;
import com.jack.login.QQ.QQLogin;
import com.jack.login.weibo.WeiBoLogin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 2017/1/5.
 */

public class LoginUtils  {
    private  static SparseArray<LoginInstance> mLoginInstance=new SparseArray<>();
    public  static  LoginInstance Login(int LoginPlafrom,Activity activity, final LoginListener listener){
        LoginInstance   instance=mLoginInstance.get(LoginPlafrom);
        if(instance!=null){
            instance.doLogin(listener);
            return instance;
        }
        switch (LoginPlafrom){
            case Constant.LoginPlatform.QQ:
                instance=new QQLogin(activity);
                break;
            case Constant.LoginPlatform.WeiBo:
                instance=new WeiBoLogin(activity);
                break;
        }
        mLoginInstance.put(LoginPlafrom,instance);
        instance.doLogin(listener);
        return instance;
    }


}
