package com.jack.Cache;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/25.
 */

public interface ICache {

    <T> Observable<T> get(String key, Class<T> cls);

    <T> void put(String key, T t);

}
