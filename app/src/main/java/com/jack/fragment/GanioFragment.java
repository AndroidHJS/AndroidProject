package com.jack.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.adapter.GanHuoAdapter;
import com.jack.bean.GanHuoBean;
import com.jack.bean.GanIO;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
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
        initRecyclerStyle();
        requestData(PAGE_NUM);
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

    }

    /**
     * 初始化RecyclerView样式
     */
    private void initRecyclerStyle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActiviy);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallGridPulse);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreEnabled(true);
    }

    /**
     * 绑定数据
     * @param data
     */
    private  void bindGanHuoData(List<GanHuoBean> data){
        if(mAdapter==null){
            mAdapter=new GanHuoAdapter(mActiviy,data);
            mXRecyclerView.setAdapter(mAdapter);
            return;
        }
        if(!mIsLoadRefresh){
            mXRecyclerView.refreshComplete();
            mIsLoadRefresh=true;
        }else{
            mAdapter.addData(data);
            mXRecyclerView.loadMoreComplete();
        }


    }

    /**
     * 请求数据数据
     * @param page 页数
     */
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
