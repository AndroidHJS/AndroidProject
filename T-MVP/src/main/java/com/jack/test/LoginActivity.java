package com.jack.test;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jack.test.base.BaseActivity;
import com.jack.test.login.LoginContract;
import com.jack.test.login.LoginModel;
import com.jack.test.login.LoginPresenter;


public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContract.View{
    EditText mEtUserName;
    EditText mEtPassWorld;
    Button mBtnLogin;
    private  AlertDialog.Builder  mBuilder;
    private  AlertDialog mDialog;
    @Override
    public void init() {
        mBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        mBuilder.setTitle("登录");
        mBuilder.setMessage("正在登录 请稍等");
    }

    @Override
    public void initView() {
        mBtnLogin= (Button) this.findViewById(R.id.btn_login);
        mEtUserName= (EditText) this.findViewById(R.id.et_userName);
        mEtPassWorld= (EditText) this.findViewById(R.id.et_passWorld);

    }

    @Override
    public void initData() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=getUserName(mEtUserName);
                String  passWorld=getPassWorld(mEtPassWorld);
                if (userName==null||userName.equals("")){
                    Toast.makeText(mActivity,"用户名为空,请填写",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passWorld==null||passWorld.equals("")){
                    Toast.makeText(mActivity,"密码为空,请填写!",Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(mActivity, userName, passWorld, new LoginContract.LoginListener() {
                    @Override
                    public void loginSuccess() {
                        Toast.makeText(mActivity,"登录成功了",Toast.LENGTH_SHORT).show();
                        ReqeustEnd();
                    }

                    @Override
                    public void loginFail() {
                        Toast.makeText(mActivity,"登录失败",Toast.LENGTH_SHORT).show();
                        ReqeustEnd();
                    }
                });
            }
        });
    }
    @Override
    public void RequstStart() {
        mDialog= mBuilder.show();

    }

    @Override
    public void ReqeustEnd() {
        mDialog.dismiss();

    }

    @Override
    public void onInternetError() {

    }

    @Override
    public void ReqeustError() {

    }



    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }
    @Override
    public String getUserName(EditText editText) {
        String userName=null;
        if(editText!=null){
            userName= editText.getText().toString().trim();
        }
        return userName;
    }

    @Override
    public String getPassWorld(EditText editText) {
        String  passWorld =null;
        if(editText!=null){
            passWorld= editText.getText().toString().trim();
        }
        return passWorld;
    }
}
