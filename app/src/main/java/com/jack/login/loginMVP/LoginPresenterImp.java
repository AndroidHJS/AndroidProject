package com.jack.login.loginMVP;

import android.text.TextUtils;

import com.jack.login.Base.LoginListener;
import com.jack.login.LoginResult;
import com.jack.utils.ToastUtils;

/**
 * Created by jack on 2017/1/10.
 */

public class LoginPresenterImp implements  LoginProtocol.LogingPresenter {
    private  LoginProtocol.LogingModel  mLogingModel;
    private LoginProtocol.LoginView  mLoginView;
    public  LoginPresenterImp(LoginProtocol.LoginView loginView){
        this.mLoginView=loginView;
        this.mLogingModel=new LoginModelImp();
    }
    @Override
    public void LoginPhone(String phone, String passworld) {
        if(TextUtils.isEmpty(passworld)) {
            ToastUtils.showToast("密码为空");
            return;
        }
        if(TextUtils.isEmpty(phone)) {
            ToastUtils.showToast("手机号为空");
            return;
        }
        mLoginView.showLoginDialog();
        mLogingModel.LoginPhone(phone, passworld, new LoginListener() {
            @Override
            public void LoginCanlce() {
                mLoginView.dissmissLoginDialog();
            }

            @Override
            public void LoginError(Exception e) {
                mLoginView.dissmissLoginDialog();
            }

            @Override
            public void LoginSuccess(LoginResult result) {
                mLoginView.dissmissLoginDialog();
                ToastUtils.showToast("登录成功");
            }
        });


    }
}
