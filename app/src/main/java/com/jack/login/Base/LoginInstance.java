package com.jack.login.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jack on 2017/1/5.
 */

public abstract class LoginInstance {
    public  Activity mActivity;
    public  LoginInstance(Activity activity){
        this.mActivity=activity;
    }
    public abstract void doLogin(LoginListener mListener);
    public abstract void getUserInfo(BaseToken token, LoginListener listener);
    public  abstract  void  handleResult(int requestCode, int resultCode, Intent data);
    public abstract boolean isInstance(Context context);
    public  abstract void recycle();
    public abstract String buildUrl(BaseToken token);


}
