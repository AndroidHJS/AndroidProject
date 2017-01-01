package com.jack.UI.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jack.Net.RetrofitHelper;
import com.jack.UI.adapter.InternetSafeAdapter;
import com.jack.bean.InternetSafe;
import com.jack.UI.base.BaseFragment;
import com.jack.main.R;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/18.
 */

public class InternetSafeFragment extends BaseFragment<InternetSafe> {
    @Bind(R.id.recycler_view_internet_safe)
    RecyclerView mRecyclerView;
    @Bind(R.id.layout_refresh)
    SwipeRefreshLayout mRefreshLayout;
    private InternetSafeAdapter mAdapter;
    private  boolean mIsRefresh=false;
    private  String id;
    @Override
    public View createSuccessView(ViewGroup container) {
        View view=View.inflate(mActivity,R.layout.fragment_internet_safe,container);
        return view;
    }
    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout.setOnRefreshListener(this);
    }
    @Override
    public Observable<InternetSafe> getObservable() {
         String  id=getArguments().getString("id").trim();
        if(id==null||id.equals("")){
            id="11";
        }
        return  RetrofitHelper.getInternetSafeList(id);
    }
    @Override
    public void bindData(InternetSafe internetSafe) {
        if(mIsRefresh){
            mIsRefresh=false;
            mRefreshLayout.setRefreshing(false);
        }
        mAdapter=new InternetSafeAdapter(internetSafe.getStories());
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        mIsRefresh=true;

    }
    public  static InternetSafeFragment getIntance(String id) {
        InternetSafeFragment myFragment = new InternetSafeFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        myFragment.setArguments(args);
        return myFragment;
    }
}
