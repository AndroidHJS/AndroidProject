package com.jack.Net;



/**
 * Created by Administrator on 2016/12/5.
 */

public interface RequestListener {
    void onSuccess(Object obj);
    void onFail(Throwable throwable);
}
