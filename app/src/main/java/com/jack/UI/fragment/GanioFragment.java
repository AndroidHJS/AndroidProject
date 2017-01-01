package com.jack.UI.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import rx.Observable;


/**
 * Created by Administrator on 2016/12/4.
 */

public class GanioFragment extends BaseFragment<GanIO> {
    @Bind(R.id.recyclerview_ganHuo)
    XRecyclerView mXRecyclerView;
    private static  int PAGE_NUM = 1;
    private static final int PAGE = 5;
    private GanHuoAdapter mAdapter;
    private  boolean mIsRefresh=false;
    private  View mView;
    private GanIO mData;
    @Override
    public View createSuccessView(ViewGroup container) {
        mView=View.inflate(mActivity,R.layout.fragment_ganio,container);
         return mView;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setLoadingListener(this);
        mAdapter=new GanHuoAdapter(new ArrayList<GanHuoBean>());
        mXRecyclerView.setAdapter(mAdapter);
        mXRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void bindData(GanIO ganIO) {
        mData=ganIO;
        mAdapter.addData(ganIO.getResults(),mIsRefresh);
        if(mIsRefresh){
            mXRecyclerView.refreshComplete();
        }else{
            mXRecyclerView.loadMoreComplete();

        }
    }

    @Override
    public GanIO getData() {
        return mData;
    }

    @Override
    public Observable<GanIO> getObservable() {
        String  category=getArguments().getString("category").trim();
        if(category==null||category.equals("")){
            category="Android";
        }
        return  RetrofitHelper.getGanHuoList(category,PAGE_NUM);
    }
    @Override
    public XRecyclerView getXRecyclerView() {
        return mXRecyclerView;
    }

    @Override
    public void onRefresh() {
        mIsRefresh=true;
        PAGE_NUM=1;
        super.onRefresh();
    }

    @Override
    public void onLoadMore() {
        mIsRefresh=false;
        PAGE_NUM++;
       super.onLoadMore();

    }
    public  static GanioFragment getIntance(String category) {
        GanioFragment myFragment = new GanioFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        myFragment.setArguments(args);
        return myFragment;
    }
}
