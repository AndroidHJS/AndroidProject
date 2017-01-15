package com.jack.login.QQ;

import com.jack.login.Base.BaseToken;

import org.json.JSONObject;

/**
 * Created by jack on 2017/1/5.
 */

public class QQLoginToken extends BaseToken<JSONObject> {
    @Override
    public void parse(JSONObject jsonObj){
        String access_token=jsonObj.optString("access_token");
        String openId=jsonObj.optString("openid");
        this.setAccess_token(access_token);
        this.setOpen_id(openId);
    }
}
