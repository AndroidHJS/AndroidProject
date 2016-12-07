package com.jack.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.adapter.GanHuoAdapter;
import com.jack.bean.GanHuoBean;
import com.jack.bean.GanIO;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2016/12/4.
 */

public class GanioFragment extends BaseFragment {
    @Bind(R.id.recyclerview_ganHuo)
    XRecyclerView mXRecyclerView;
    private static  int PAGE_NUM = 1;
    private static final int PAGE = 20;
    private GanHuoAdapter mAdapter;
    private  boolean mIsLoadRefresh=true;
    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_ganio,null);
        return view;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActiviy);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsLoadRefresh=false;
                PAGE_NUM=1;
                requestData(PAGE_NUM);
            }

            @Override
            public void onLoadMore() {
                PAGE_NUM++;
                requestData(PAGE_NUM);
            }
        });
        requestData(PAGE_NUM);
    }
    private  void bindGanHuoData(List<GanHuoBean> mData){
        if(!mIsLoadRefresh){
            mAdapter=new GanHuoAdapter(mActiviy,mData);
            mXRecyclerView.setAdapter(mAdapter);
            mIsLoadRefresh=true;
            mXRecyclerView.refreshComplete();
            return;
        }
        if(mAdapter==null){
            mAdapter=new GanHuoAdapter(mActiviy,mData);
            mXRecyclerView.setAdapter(mAdapter);
            mAdapter.addData(mData);
            mXRecyclerView.loadMoreComplete();
        }




    }
    private void requestData(int page){
        RetrofitHelper.getGanHuoInfo(new RequestListener() {
            @Override
            public void onSuccess(Object obj) {
                if(obj instanceof GanIO){
                    GanIO ganHuo= (GanIO) obj;
                    bindGanHuoData(ganHuo.getResults());

                }

            }

            @Override
            public void onFail(Throwable throwable) {

            }
        },PAGE,page, GanIO.class);

    }
}
