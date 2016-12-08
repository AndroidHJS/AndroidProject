package com.jack.comment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jack.Net.RequestListener;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/4.
 */

public abstract  class BaseFragment extends Fragment implements RequestListener{
    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity =(Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getView(inflater);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }
    public  abstract  View getView(LayoutInflater inflater);
    public abstract  void initData();
    public abstract  void bindData(Object obj);


    @Override
    public void onSuccess(Object obj) {
        bindData(obj);

    }

    @Override
    public void onFail(Throwable throwable) {
        Toast.makeText(mActivity,"请求数据出错",Toast.LENGTH_LONG).show();
    }
}
