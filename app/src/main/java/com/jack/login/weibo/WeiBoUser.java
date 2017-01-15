package com.jack.login.weibo;

import android.text.TextUtils;

import com.jack.login.Base.User;
import org.json.JSONObject;

/**
 * Created by jack on 2017/1/8.
 */

public class WeiBoUser extends User {
    public WeiBoUser parse(JSONObject jsonObject) {
        this.setOpenId(String.valueOf(jsonObject.optInt("id")));
        this.setNickname(jsonObject.optString("screen_name"));
        String gender = jsonObject.optString("gender");
        if (TextUtils.equals(gender, "m")) {
            this.setSex(1);
        } else if (TextUtils.equals(gender, "f")) {
            this.setSex(2);
        } else {
            this.setSex(0);
        }
        this.setHeadImageUrl(jsonObject.optString("profile_image_url"));
        return this;
    }

}
