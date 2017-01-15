package com.jack.login.Base;

import com.jack.login.LoginResult;

/**
 * Created by jack on 2017/1/5.
 */

public interface LoginListener {
    void LoginCanlce();
    void  LoginError(Exception e);
    void LoginSuccess(LoginResult result);
}
