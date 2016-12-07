package com.jack.comment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/4.
 */

public abstract  class BaseFragment extends Fragment {
    public Activity mActiviy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActiviy=(Activity) context;
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

}
