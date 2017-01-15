package com.jack.login;

import com.jack.login.Base.BaseToken;
import com.jack.login.Base.User;

/**
 * Created by jack on 2017/1/5.
 */

public class LoginResult {
    private BaseToken mToken;
    private User mUser;
    public LoginResult(BaseToken token,User mUser){
        this.mToken=token;
        this.mUser=mUser;
    }

    public BaseToken getmToken() {
        return mToken;
    }

    public void setmToken(BaseToken mToken) {
        this.mToken = mToken;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
