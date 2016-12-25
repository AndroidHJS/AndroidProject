package com.jack.UI.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jack.UI.holder.MainBannerItemHolder;
import com.jack.bean.TopSotriesBean;
import com.jack.main.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MainBannerAdapter extends PagerAdapter{
    private List<TopSotriesBean> mTopBeans;
    private Context mContext;
    private MainBannerItemHolder mMainBannerHolder;
    public  MainBannerAdapter(List<TopSotriesBean> topSotriesBeen,Context context){
        this.mTopBeans=topSotriesBeen;
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return mTopBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(mContext, R.layout.item_banner,null);
        mMainBannerHolder=new MainBannerItemHolder(view);
        container.addView(view);
        mMainBannerHolder.bindData(mTopBeans.get(position));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }


}
