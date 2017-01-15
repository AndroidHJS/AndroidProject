package com.jack.UI.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jack.main.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/6.
 */

public abstract  class BaseActivity extends AppCompatActivity {
    Bundle mSavedInstanceState;
    public Bundle getSavedInstanceState(){
        return mSavedInstanceState ;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState=savedInstanceState;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        initData();
    }

    public  abstract void initData();
    public abstract void init();
    public abstract int  getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    public   void  showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
