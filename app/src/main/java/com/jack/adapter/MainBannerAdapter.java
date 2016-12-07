package com.jack.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.bean.TopSotriesBean;
import com.jack.main.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MainBannerAdapter extends PagerAdapter{
    private List<TopSotriesBean> mTopBeans;
    private Context mContext;
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
        container.addView(view);
        bindSimpleData(position,view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
    private void bindSimpleData(int position,View view){
        ImageView bannerImg;
        TextView TvTitle;
        if(mTopBeans!=null){
            TopSotriesBean  tempBean=mTopBeans.get(position);
            bannerImg= (ImageView) view.findViewById(R.id.img_banner_img);
            TvTitle= (TextView) view.findViewById(R.id.tv_banner_title);
            Glide.with(mContext).load(tempBean.getImage()).into(bannerImg);
            TvTitle.setText(tempBean.getTitle());


        }

    }

}
