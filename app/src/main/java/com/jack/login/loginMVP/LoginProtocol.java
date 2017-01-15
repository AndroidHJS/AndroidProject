package com.jack.login.loginMVP;

import android.widget.EditText;

import com.jack.login.Base.LoginListener;

/**
 * Created by jack on 2017/1/10.
 */

public interface LoginProtocol {
    interface   LoginView{
        String  getPhoneOrPassWorld(EditText editText);
        void  showLoginDialog();
        void dissmissLoginDialog();
    }
    interface   LogingPresenter{
        void  LoginPhone(String  phone, String passworld);
    }
    interface   LogingModel{
        void  LoginPhone(String phone,String passworld,LoginListener  loginListener);
    }
}
