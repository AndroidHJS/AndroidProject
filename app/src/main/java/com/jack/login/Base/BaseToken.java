package com.jack.login.Base;

import org.json.JSONObject;

/**
 * Created by jack on 2017/1/5.
 */

public  abstract class BaseToken<T> {
    private  String access_token;
    private String open_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }
    public  abstract void parse(T  t);
}
