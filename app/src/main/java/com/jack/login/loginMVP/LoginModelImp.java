package com.jack.login.loginMVP;

import android.os.SystemClock;
import android.util.Log;

import com.jack.login.Base.LoginListener;
import com.jack.login.Base.User;
import com.jack.login.LoginResult;
import com.jack.utils.RxJavaUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by jack on 2017/1/10.
 */

public class LoginModelImp implements LoginProtocol.LogingModel {
    @Override
    public void LoginPhone(final String phone,final String passworld,final LoginListener loginListener) {
        Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                SystemClock.sleep(2*1000);
                User user=null;
                if(phone.equals("123456")&&passworld.equals("123")){
                    user=new User();
                }
                subscriber.onNext(user);
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<User>applySchedulers()).subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                loginListener.LoginCanlce();
            }

            @Override
            public void onError(Throwable e) {
                loginListener.LoginError(new Exception("手机登录失败"));
            }

            @Override
            public void onNext(User user) {
                loginListener.LoginSuccess(new LoginResult(null,user));

            }
        });

    }
}
