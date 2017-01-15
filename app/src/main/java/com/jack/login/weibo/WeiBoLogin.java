package com.jack.login.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jack.global.Constant;
import com.jack.login.Base.BaseToken;
import com.jack.login.Base.LoginInstance;
import com.jack.login.Base.LoginListener;
import com.jack.login.Base.User;
import com.jack.login.LoginResult;
import com.jack.utils.RxJavaUtils;
import com.jack.utils.ToastUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by jack on 2017/1/8.
 */

public class WeiBoLogin extends LoginInstance {
    private SsoHandler mSsoHandler;
    private String mWeiBoRedirectUrl = "https://api.weibo.com/oauth2/default.html";
    private static final String USER_INFO = "https://api.weibo.com/2/users/show.json";
    private AuthInfo mAuthInfo;
    private LoginListener  mLoginListener;
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
    private WeiboAuthListener mListener=new WeiboAuthListener() {
        @Override
        public void onComplete(Bundle bundle) {
            ToastUtils.showToast("授权成功");
            Oauth2AccessToken OauthToken = Oauth2AccessToken.parseAccessToken(bundle);
            WeiBoLoginToken  token=new WeiBoLoginToken();
            token.parse(OauthToken);
            getUserInfo(token,mLoginListener);

        }

        @Override
        public void onWeiboException(WeiboException e) {
            mLoginListener.LoginError(e);

        }

        @Override
        public void onCancel() {
            mLoginListener.LoginCanlce();

        }
    };
    public WeiBoLogin(Activity activity) {
        super(activity);
        mAuthInfo=new AuthInfo(activity, Constant.AppKey.wei_bo_app_key,
                mWeiBoRedirectUrl, SCOPE);
        mSsoHandler=new SsoHandler(activity,mAuthInfo);
    }

    @Override
    public void doLogin(LoginListener mListener) {
        this.mLoginListener=mListener;
        mSsoHandler.authorize(this.mListener);
    }

    @Override
    public void getUserInfo(final BaseToken token, final LoginListener listener) {
        Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                 OkHttpClient client=new OkHttpClient();
                 Request  request=new Request.Builder().url(buildUrl(token)).build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONObject jsonObj=new JSONObject(response.body().string());
                    WeiBoUser user=new WeiBoUser();
                    user.parse(jsonObj);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }


            }
        }).compose(RxJavaUtils.<User>applySchedulers()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                listener.LoginSuccess(new LoginResult(token,user));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                listener.LoginError(new Exception("获取微博用户信息出错了"));
            }
        });

    }

    @Override
    public void handleResult(int requestCode, int resultCode, Intent data) {
        mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
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
        return USER_INFO + "?access_token=" + token.getAccess_token() + "&uid=" + token.getOpen_id();
    }
}
