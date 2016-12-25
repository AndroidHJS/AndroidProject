package com.jack.UI.holder;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.jack.UI.adapter.MainBannerAdapter;
import com.jack.bean.TopSotriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/24.
 */

public class MainBannerHolder extends BaseRecyclerViewHolder<List<TopSotriesBean>>{
    private MainBannerAdapter mMainBannerAdapter;
    @Bind(R.id.viewpager_banner)
    ViewPager mViewPagerBanner;
    public MainBannerHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(List<TopSotriesBean> topSotriesBeen) {
        if(topSotriesBeen==null||topSotriesBeen.size()==0){
            return;
        }
        mMainBannerAdapter=new MainBannerAdapter(topSotriesBeen,mContext);
        mViewPagerBanner.setAdapter(mMainBannerAdapter);
    }
}
