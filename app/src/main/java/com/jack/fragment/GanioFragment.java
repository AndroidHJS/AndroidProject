package com.jack.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jack.Net.RetrofitHelper;
import com.jack.adapter.GanHuoAdapter;
import com.jack.bean.GanIO;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
    private  boolean mIsLoadRefresh=true;
    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_ganio,null);
        return view;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallGridPulse);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreEnabled(true);
        RetrofitHelper.getGanHuoList(this,PAGE,PAGE, GanIO.class);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsLoadRefresh=false;
                PAGE_NUM=1;
                RetrofitHelper.getGanHuoList(GanioFragment.this,PAGE,PAGE_NUM, GanIO.class);
            }

            @Override
            public void onLoadMore() {
             PAGE_NUM++;
             RetrofitHelper.getGanHuoList(GanioFragment.this,PAGE,PAGE_NUM, GanIO.class);
            }
        });

    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof GanIO){
            GanIO ganHuo= (GanIO) obj;
            if(mAdapter==null){
                mAdapter=new GanHuoAdapter(ganHuo.getResults());
                mXRecyclerView.setAdapter(mAdapter);
                return;
            }
            if(!mIsLoadRefresh){
                mXRecyclerView.refreshComplete();
                mIsLoadRefresh=true;
            }else{
                mAdapter.addData(ganHuo.getResults());
                mXRecyclerView.loadMoreComplete();
            }

        }

    }


}
