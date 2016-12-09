package com.jack.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jack.Net.RetrofitHelper;
import com.jack.adapter.MeiZhiAdapter;
import com.jack.bean.MeiZhi;
import com.jack.bean.MeiZhiBean;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/4.
 */

public class MeiZiFragment extends BaseFragment {
    @Bind(R.id.RecyclerView_meizhi)
    XRecyclerView  mXRecyclerView;
    private MeiZhiAdapter mAdapter;
    private static final int PAGE_NUM=20;
    private     int PAGE=1;
    private  boolean mIsRefresh=false;

    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_meizi,null);
        return view;
    }

    @Override
    public void initData() {
        RetrofitHelper.getMeiZhiList(this,PAGE_NUM,PAGE, MeiZhi.class);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh=true;
                PAGE=1;
                RetrofitHelper.getMeiZhiList(MeiZiFragment.this,PAGE_NUM,PAGE, MeiZhi.class);
            }

            @Override
            public void onLoadMore() {
                mIsRefresh=false;
                PAGE++;
                RetrofitHelper.getMeiZhiList(MeiZiFragment.this,PAGE_NUM,PAGE, MeiZhi.class);
            }
        });
        mAdapter=new MeiZhiAdapter(new ArrayList<MeiZhiBean>());
        mXRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mXRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof MeiZhi){
            MeiZhi meiZhi= (MeiZhi) obj;
            mAdapter.addData(meiZhi.getResults(),mIsRefresh);
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
