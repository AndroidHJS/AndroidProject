package com.jack.Net;



/**
 * Created by Administrator on 2016/12/5.
 */

public interface RequestListener<T> {
    void onSuccess(T t);
    void onFail(Throwable throwable);
}
