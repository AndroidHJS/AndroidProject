package com.jack.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jack.Dialag.LoginDialog;
import com.jack.UI.base.BaseActivity;
import com.jack.global.Constant;
import com.jack.global.UserInfo;
import com.jack.login.Base.LoginInstance;
import com.jack.login.Base.LoginListener;
import com.jack.login.loginMVP.LoginPresenterImp;
import com.jack.login.loginMVP.LoginProtocol;
import com.jack.main.R;
import com.jack.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginProtocol.LoginView{
    @Bind(R.id.btn_QQ)
    ImageButton mBtnQQ;
    @Bind(R.id.btn_weibo)
    ImageButton mBtnWeiBo;
    @Bind(R.id.btn_weixing)
    ImageButton mBtnWeChat;
    @Bind(R.id.btn_phone)
    Button  mBtnPhone;
    @Bind(R.id.edit_phone)
    EditText  mEtPhone;
    @Bind(R.id.edit_passwold)
    EditText  mEtPassWorld;
    private LoginDialog  mLoginDialg;
    LoginInstance mLoginInstance;
    private  LoginProtocol.LogingPresenter  mLogingPresenter;
    private  int  mCurrentLogin=Constant.LoginPlatform.QQ;
    private  LoginListener mLoginListener=new LoginListener() {
        @Override
        public void LoginCanlce() {
            ToastUtils.showToast("用户取消授权登录");

        }

        @Override
        public void LoginError(Exception e) {
            ToastUtils.showToast(e.toString());
        }

        @Override
        public void LoginSuccess(LoginResult result) {
          UserInfo.getInstance().saveUser(result.getmUser());
          System.out.println("用户信息保存成功");
          Intent intent=new Intent();
          intent.putExtra("user",result.getmUser());
          setResult(Activity.RESULT_OK,intent);
          finish();
        }
    };
    @Override
    public void initData() {
        mBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String  phoneStr=getPhoneOrPassWorld(mEtPhone);
                  String   passWorldStr=getPhoneOrPassWorld(mEtPassWorld);
                  mLogingPresenter.LoginPhone(phoneStr,passWorldStr);
            }
        });

    }

    @Override
    public void init() {
        mLoginDialg=new LoginDialog(this,R.style.dialog);
        mLogingPresenter=new LoginPresenterImp(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_weibo,R.id.btn_QQ})
    public  void startLogin(View view){
        ToastUtils.showToast("进行登录");
        switch (view.getId()){
            case R.id.btn_QQ:
                mCurrentLogin=Constant.LoginPlatform.QQ;
             break;
            case R.id.btn_weibo:
                mCurrentLogin=Constant.LoginPlatform.WeiBo;
                break;
        }
        mLoginInstance=LoginUtils.Login(mCurrentLogin,this,mLoginListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginInstance.handleResult(requestCode,resultCode,data);
    }

    @Override
    public String getPhoneOrPassWorld(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public void showLoginDialog() {
        mLoginDialg.show();

    }

    @Override
    public void dissmissLoginDialog() {
        mLoginDialg.dismiss();

    }
}
