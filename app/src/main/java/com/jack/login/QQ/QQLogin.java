package com.jack.login.QQ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.jack.AppApplication;
import com.jack.global.Constant;
import com.jack.login.Base.BaseToken;
import com.jack.login.Base.LoginInstance;
import com.jack.login.Base.LoginListener;
import com.jack.login.LoginResult;
import com.jack.utils.RxJavaUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by jack on 2017/1/5.
 */

public class QQLogin extends LoginInstance {
    private static final String URL = "https://graph.qq.com/user/get_user_info";
    private Tencent mTencent;
    private IUiListener mIUiListener;
    private static final String SCOPE = "get_simple_userinfo";
    public QQLogin(Activity activity) {
        super(activity);
        mTencent=Tencent.createInstance(Constant.AppKey.QQ_app_key,activity);

    }

    @Override
    public void doLogin(final LoginListener listener) {
        mIUiListener=new IUiListener() {
            @Override
            public void onComplete(Object o) {
                QQLoginToken  token=new QQLoginToken();
                token.parse((JSONObject) o);
                getUserInfo(token,listener);
            }

            @Override
            public void onError(UiError uiError) {
                listener.LoginError(new Exception("qq 登录失败"));
            }

            @Override
            public void onCancel() {
                listener.LoginCanlce();
            }
        };
        mTencent.login(mActivity,SCOPE,mIUiListener);

    }

    @Override
    public void getUserInfo(final BaseToken token, final LoginListener listener) {
        Observable.create(new Observable.OnSubscribe<QQUser>() {
            @Override
            public void call(Subscriber<? super QQUser> subscriber) {
                OkHttpClient clien=new OkHttpClient();
                Request  request=new Request.Builder().url(buildUrl(token)).build();
                try {
                    Response response = clien.newCall(request).execute();
                    String resposeStr = response.body().string();
                    JSONObject jsonObj=new JSONObject(resposeStr);
                    QQUser user=new QQUser();
                    user.parse(token.getOpen_id(), jsonObj);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).compose(RxJavaUtils.<QQUser>applySchedulers()).subscribe(new Subscriber<QQUser>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.LoginError(new Exception("获取QQ用户信息失败"));

            }

            @Override
            public void onNext(QQUser qqUser) {
                listener.LoginSuccess(new LoginResult(token,qqUser));
            }
        });

    }

    @Override
    public void handleResult(int requestCode, int resultCode, Intent data) {
       Tencent.handleResultData(data,mIUiListener);
    }

    @Override
    public boolean isInstance(Context context) {
        return false;
    }

    @Override
    public void recycle() {

    }

    @Override
    public String buildUrl(BaseToken token) {
        return URL
                + "?access_token="
                + token.getAccess_token()
                + "&oauth_consumer_key="
                + Constant.AppKey.QQ_app_key
                + "&openid="
                + token.getOpen_id()+"&format=json";
    }
}
