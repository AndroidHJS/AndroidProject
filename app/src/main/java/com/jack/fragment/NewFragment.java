package com.jack.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.Net.RetrofitHelper;
import com.jack.adapter.MainBannerAdapter;
import com.jack.bean.New;
import com.jack.bean.StoriesBean;
import com.jack.bean.TopSotriesBean;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;
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
        RetrofitHelper.getNewLatestList(this,New.class);
    }

    @Override
    public void bindData(Object obj) {
        if(obj instanceof New){
            New news= (New) obj;
            List<TopSotriesBean> topSotriesBeans=news.getTop_stories();
            List<StoriesBean> storiesBeen=news.getStories();
            if(topSotriesBeans!=null||storiesBeen!=null){
                mMainBannerAdapter=new MainBannerAdapter(topSotriesBeans, mActivity);
                mViewPagerBanner.setAdapter(mMainBannerAdapter);
                for (int i = 0; i <storiesBeen.size() ; i++) {
                    View view=View.inflate(mActivity,R.layout.item_new,null);
                    TextView TvTitle= (TextView) view.findViewById(R.id.tv_new_title);
                    ImageView ImgNews= (ImageView) view.findViewById(R.id.img_news_img);
                    LinearLayout layoutNew= (LinearLayout) view.findViewById(R.id.layout_new);
                    final StoriesBean   item=storiesBeen.get(i);
                    Glide.with(mActivity).load(item.getImages()[0]).into(ImgNews);
                    TvTitle.setText(item.getTitle());
                    layoutNew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent();
                            intent.putExtra("newItem",item);
                            intent.setClass(mActivity, NewDatailsActivity.class);
                            mActivity.startActivity(intent);

                        }
                    });
                    mLayoutNews.addView(view);

                }
            }
        }
    }
}
