package com.jack.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.jack.UI.base.BaseFragment;
import com.jack.main.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/17.
 */

public class LoadingView extends FrameLayout {
    public static final int STATE_NET_ERROR=236;
    public static final  int STATE_LOADING=345;
    public   static  final  int STATE_SUCCESS=100;
    private  Context mContext;
    @Bind(R.id.layout_net_error)
    View mLayoutNetError;
    @Bind(R.id.layout_loading)
    View mLayoutLoading;
    View  mLayoutSuccess;
    public LoadingView(Context context) {
        super(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }
    private  void init(){
        createLoadingView();
        createNetErrorView();
        ButterKnife.bind(this,this);
    }
    private View createLoadingView(){
        View LoadingView=View.inflate(mContext, R.layout.view_loading,this);
        return  LoadingView;

    }
    private  View  createNetErrorView(){
        View NetErrorView=View.inflate(mContext, R.layout.view_net_error,this);
        return  NetErrorView;
    }
    public   void  initLoadView(BaseFragment fragment){
        fragment.createSuccessView(this);
        mLayoutSuccess =getChildAt(2);
    }
    public   void  switchLoadingView( int state){
        switch (state){
            case STATE_LOADING:
                mLayoutLoading.setVisibility(View.VISIBLE);
                mLayoutNetError.setVisibility(View.GONE);
                mLayoutSuccess.setVisibility(View.GONE);
                break;
            case  STATE_NET_ERROR:
                mLayoutLoading.setVisibility(View.GONE);
                mLayoutNetError.setVisibility(View.VISIBLE);
                mLayoutSuccess.setVisibility(View.GONE);
                break;
            case STATE_SUCCESS:
                mLayoutLoading.setVisibility(View.GONE);
                mLayoutNetError.setVisibility(View.GONE);
                mLayoutSuccess.setVisibility(View.VISIBLE);
                break;
        }
    }
}
