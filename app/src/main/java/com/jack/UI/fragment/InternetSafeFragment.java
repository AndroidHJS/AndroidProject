package com.jack.UI.fragment;

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

/**
 * Created by Administrator on 2016/12/18.
 */

public class InternetSafeFragment extends BaseFragment {
    @Bind(R.id.recycler_view_internet_safe)
    RecyclerView mRecyclerView;
    private InternetSafeAdapter mAdapter;
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
        RetrofitHelper.getInternetSafeList(this,"11",InternetSafe.class);

    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof InternetSafe){
            InternetSafe tempObj= (InternetSafe) obj;
            mAdapter=new InternetSafeAdapter(tempObj.getStories());
            mRecyclerView.setAdapter(mAdapter);

        }


    }
}
