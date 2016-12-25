package com.jack.UI.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jack.Net.RetrofitHelper;
import com.jack.UI.adapter.GanHuoAdapter;
import com.jack.bean.GanHuoBean;
import com.jack.bean.GanIO;
import com.jack.UI.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * Created by Administrator on 2016/12/4.
 */

public class GanioFragment extends BaseFragment {
    @Bind(R.id.recyclerview_ganHuo)
    XRecyclerView mXRecyclerView;
    private static  int PAGE_NUM = 1;
    private static final int PAGE = 5;
    private GanHuoAdapter mAdapter;
    private  boolean mIsRefresh=false;
    private  View mView;
    @Override
    public View createSuccessView(ViewGroup container) {
         mView=View.inflate(mActivity,R.layout.fragment_ganio,container);
         return mView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        RetrofitHelper.getGanHuoList(this,PAGE,PAGE_NUM, GanIO.class);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh=true;
                PAGE_NUM=1;
                RetrofitHelper.getGanHuoList(GanioFragment.this,PAGE,PAGE_NUM, GanIO.class);
            }

            @Override
            public void onLoadMore() {
             mIsRefresh=false;
             PAGE_NUM++;
             RetrofitHelper.getGanHuoList(GanioFragment.this,PAGE,PAGE_NUM, GanIO.class);
            }
        });
        mAdapter=new GanHuoAdapter(new ArrayList<GanHuoBean>());
        mXRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof GanIO){
            GanIO ganHuo= (GanIO) obj;
            mAdapter.addData(ganHuo.getResults(),mIsRefresh);
            if(mIsRefresh){
                mXRecyclerView.refreshComplete();
            }else{
                mXRecyclerView.loadMoreComplete();
            }
        }

    }
    @Override
    public XRecyclerView getXRecyclerView() {
        return mXRecyclerView;
    }
}
