package com.jack.UI.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jack.Net.RequestListener;
import com.jack.View.LoadingView;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;

import static com.jack.View.LoadingView.STATE_LOADING;
import static com.jack.View.LoadingView.STATE_NET_ERROR;
import static com.jack.View.LoadingView.STATE_SUCCESS;

/**
 * Created by Administrator on 2016/12/4.
 */

public abstract  class BaseFragment extends Fragment implements RequestListener{
    private static final String TAG=BaseFragment.class.getSimpleName();
    public Activity mActivity;
    private  View mView;
    private  int  mCurrentState= STATE_LOADING;
    FrameLayout mLayoutContainer;
    private LoadingView mLoadingView;
    private Boolean  mIsOnCreateView=false;
    private  Boolean mIsVisibility=false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity =(Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mIsOnCreateView=true;
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_base_view,container,false);
            mLoadingView= (LoadingView) mView.findViewById(R.id.view_loading);
            mLayoutContainer= (FrameLayout) mView.findViewById(R.id.layout_fragment_container);
            mLoadingView.initLoadView(this);
            ButterKnife.bind(this,mView);
            setRecyclerViewStyle();
        }
        Log.i(TAG,this.getClass().getSimpleName()+"=======onCreateView====== 开始加载数据");
        RequestStart();
        LazyLoading();
        return mView;
    }

    public   void  RequestStart(){
        mCurrentState=STATE_LOADING;
        mLoadingView.switchLoadingView(mCurrentState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
       super.setUserVisibleHint(isVisibleToUser);
        mIsVisibility=getUserVisibleHint();
        Log.i(TAG,this.getClass().getSimpleName()+"=======setUserVisibleHint()======"+"isVisibleToUser = [" + isVisibleToUser + "]");
        LazyLoading();
    }
    private void  LazyLoading(){
        Log.i(TAG,this.getClass().getSimpleName()+"=======LazyLoading======"+"isVisibleToUser = [" + mIsVisibility + "]"+"mIsOnCreateView=="+mIsOnCreateView);
        if(mIsVisibility&&mIsOnCreateView){
            Log.i(TAG,this.getClass().getSimpleName()+"=======正在加载数据======");
            initData();
        }
    }

    /**
     * 加载不同子类的界面
     * @param
     * @return
     */
    public  abstract  View createSuccessView(ViewGroup container);

    /**
     * 初始化数据
     */
    public abstract   void initData();


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
        mCurrentState=STATE_SUCCESS;
        mLoadingView.switchLoadingView (mCurrentState);
        bindData(obj);
    }

    @Override
    public void onFail(Throwable throwable) {
        mCurrentState=STATE_NET_ERROR;
        mLoadingView.switchLoadingView(mCurrentState);
        Toast.makeText(mActivity,"网络出错了",Toast.LENGTH_LONG).show();
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsOnCreateView=false;
    }
}
