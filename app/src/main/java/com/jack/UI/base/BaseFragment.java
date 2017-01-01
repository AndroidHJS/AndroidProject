package com.jack.UI.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jack.Net.RequestListener;
import com.jack.View.LoadingView;
import com.jack.main.R;
import com.jack.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.net.UnknownHostException;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

import static com.jack.View.LoadingView.STATE_LOADING;
import static com.jack.View.LoadingView.STATE_NET_ERROR;
import static com.jack.View.LoadingView.STATE_SUCCESS;

/**
 * Created by Administrator on 2016/12/4.
 */

public abstract  class BaseFragment<T> extends Fragment implements RequestListener<T>,SwipeRefreshLayout.OnRefreshListener,XRecyclerView.LoadingListener{
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
            RequestData();

        }
    }
      public   void RequestData(){
          Observable<T> observable = getObservable();
          observable.subscribe(new Subscriber<T>() {
              @Override
              public void onCompleted() {
                  RequestEnd();
              }

              @Override
              public void onError(Throwable e) {
                  onFail(e);

              }

              @Override
              public void onNext(T t) {
                  onSuccess(t);
              }
          });
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
     * @param
     */
    public abstract  void bindData(T t);

    /**
     * 需要XRecyclerView样式
     * @return
     */
    public  XRecyclerView getXRecyclerView(){
        return  null;
    }

    @Override
    public void onSuccess(T t) {
        mCurrentState=STATE_SUCCESS;
        mLoadingView.switchLoadingView (mCurrentState);
        bindData(t);
    }

    @Override
    public void onFail(Throwable throwable) {
        Toast.makeText(mActivity,"网络出错了",Toast.LENGTH_LONG).show();
        mCurrentState=STATE_NET_ERROR;
        RequestEnd();
        T data = getData();
        if(data==null){
            mLoadingView.switchLoadingView(mCurrentState);
            return;
        }
        XRecyclerView xRecyclerView = getXRecyclerView();
        if(xRecyclerView!=null){
           xRecyclerView.loadMoreComplete();
        }

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

    public  T getData(){
        T t=null;
      return t;
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsOnCreateView=false;
    }

    @Override
    public void onRefresh() {
        ToastUtils.showToast("开始刷新");
        RequestData();
    }

    @Override
    public void onLoadMore() {
        ToastUtils.showToast("开始加载更多");
        RequestData();

    }
    public  void  RequestEnd(){

    }
    public abstract Observable<T> getObservable();
}
