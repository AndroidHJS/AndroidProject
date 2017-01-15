package com.jack.login.QQ;

import android.text.TextUtils;

import com.jack.login.Base.User;

import org.json.JSONObject;

/**
 * Created by jack on 2017/1/5.
 */

public class QQUser extends User {
    public  QQUser parse(String openId, JSONObject jsonObject) {
        this.setNickname(jsonObject.optString("nickname"));
        this.setOpenId(openId);
        this.setSex(TextUtils.equals("男", jsonObject.optString("gender")) ? 1 : 2);
        this.setHeadImageUrl(jsonObject.optString("figureurl_qq_1"));
        return this;
    }

}
