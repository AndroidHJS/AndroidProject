package com.jack.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jack.Net.RetrofitHelper;
import com.jack.adapter.MeiZhiAdapter;
import com.jack.bean.MeiZhi;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/4.
 */

public class MeiZiFragment extends BaseFragment {
    @Bind(R.id.RecyclerView_meizhi)
    XRecyclerView  mXRecyclerView;
    private MeiZhiAdapter mAdapter;
    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_meizi,null);
        return view;
    }

    @Override
    public void initData() {
        RetrofitHelper.getMeiZhiList(this,5,1, MeiZhi.class);
    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof MeiZhi){
            MeiZhi meiZhi= (MeiZhi) obj;
            mAdapter=new MeiZhiAdapter(meiZhi.getResults());
            mXRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            mXRecyclerView.setAdapter(mAdapter);
        }

    }


}
