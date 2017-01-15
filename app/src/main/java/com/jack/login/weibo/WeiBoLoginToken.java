package com.jack.login.weibo;

import com.jack.bean.BaseBean;
import com.jack.login.Base.BaseToken;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;



/**
 * Created by jack on 2017/1/8.
 */

public class WeiBoLoginToken extends BaseToken<Oauth2AccessToken>{
    private  String mRefreshToken;
    private String phoneNum;
    @Override
    public void parse(Oauth2AccessToken   token){
        this.setOpen_id(token.getUid());
        this.setAccess_token(token.getToken());
        this.mRefreshToken=token.getRefreshToken();
        this.phoneNum=token.getPhoneNum();
        this.phoneNum=token.getPhoneNum();
        this.mRefreshToken=token.getRefreshToken();

    }


}
