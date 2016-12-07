package com.jack.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.adapter.MainBannerAdapter;
import com.jack.bean.New;
import com.jack.bean.StoriesBean;
import com.jack.bean.TopSotriesBean;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
import com.jack.mymy.MainActivity;
import com.jack.mymy.NewDatailsActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/4.
 */

public class NewFragment extends BaseFragment {
    @Bind(R.id.viewpage_bannne)
    ViewPager mViewPagerBanner;
    private MainBannerAdapter mMainBannerAdapter;
    @Bind(R.id.layout_stority)
    LinearLayout mLayoutNews;

    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_new,null);
        return view;
    }

    @Override
    public void initData() {
        RetrofitHelper.getNewLatest(new RequestListener() {
            @Override
            public void onSuccess(Object obj) {
                if(obj instanceof New){
                    New news= (New) obj;
                    bindBannerDate(news.getTop_stories());
                    bindNewsData(news.getStories());

                }




            }

            @Override
            public void onFail(Throwable throwable) {

            }


        },New.class);


    }
    private void  bindBannerDate(List<TopSotriesBean> data ){
        mMainBannerAdapter=new MainBannerAdapter(data,mActiviy);
        mViewPagerBanner.setAdapter(mMainBannerAdapter);
    }
    private void bindNewsData(List<StoriesBean> StoriesBean){
        if(StoriesBean==null){
            return;
        }
        for (int i = 0; i <StoriesBean.size() ; i++) {
            View view=View.inflate(mActiviy,R.layout.item_new,null);
            initNewView(view,StoriesBean.get(i));
            mLayoutNews.addView(view);

        }

    }

    private void initNewView(View view,final  StoriesBean item){
        TextView TvTitle= (TextView) view.findViewById(R.id.tv_new_title);
        ImageView ImgNews= (ImageView) view.findViewById(R.id.img_news_img);
        LinearLayout layoutNew= (LinearLayout) view.findViewById(R.id.layout_new);
        TextView TvNewTime= (TextView) view.findViewById(R.id.tv_new_time);
        Glide.with(mActiviy).load(item.getImages()[0]).into(ImgNews);
        TvTitle.setText(item.getTitle());
        layoutNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("newItem",item);
                intent.setClass(mActiviy, NewDatailsActivity.class);
                mActiviy.startActivity(intent);

            }
        });


    }


}
