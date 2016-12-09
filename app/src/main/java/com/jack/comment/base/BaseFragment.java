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
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
        setRecyclerViewStyle();
        return view;
    }

    /**
     * 加载不同子类的界面
     * @param inflater
     * @return
     */
    public  abstract  View getView(LayoutInflater inflater);

    /**
     * 初始化数据
     */
    public abstract  void initData();

    /**
     * 绑定数据
     * @param obj
     */
    public abstract  void bindData(Object obj);

    /**
     * 需要XRecyclerView样式
     * @return
     */
    public  XRecyclerView getXRecyclerView(){
        return  null;
    }


    @Override
    public void onSuccess(Object obj) {
        bindData(obj);


    }

    @Override
    public void onFail(Throwable throwable) {
        Toast.makeText(mActivity,"请求数据出错",Toast.LENGTH_LONG).show();
    }
    private void setRecyclerViewStyle(){
        XRecyclerView xRecyclerView=getXRecyclerView();
        if(xRecyclerView!=null){
            xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallGridPulse);
            xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            xRecyclerView.setLoadingMoreEnabled(true);
            xRecyclerView.setPullRefreshEnabled(true);
        }
    }
}
