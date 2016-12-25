package com.jack.UI.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jack.Net.RetrofitHelper;
import com.jack.UI.holder.MainBannerHolder;
import com.jack.UI.holder.NewestNewHolder;
import com.jack.bean.New;
import com.jack.bean.StoriesBean;
import com.jack.bean.TopSotriesBean;
import com.jack.UI.base.BaseFragment;
import com.jack.main.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/4.
 */

public class NewFragment extends BaseFragment {
    private View mView;
    private MainBannerHolder mMainBannerHolder;
    private NewestNewHolder mNewstNewHolder;
    @Bind(R.id.layout_new_container)
    LinearLayout mLayoutNewContainer;
    @Override
    public View createSuccessView( ViewGroup container) {
        mView=View.inflate(mActivity,R.layout.fragment_new,container);
        return mView;
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
            View MainBannerView=LayoutInflater.from(mActivity).inflate(R.layout.holder_main_banner,mLayoutNewContainer);
            mMainBannerHolder=new MainBannerHolder(MainBannerView);
            View  NewstNewView=LayoutInflater.from(mActivity).inflate(R.layout.holder_newest_new,mLayoutNewContainer);
            mNewstNewHolder=new NewestNewHolder(NewstNewView);
            mMainBannerHolder.bindData(topSotriesBeans);
            mNewstNewHolder.bindData(storiesBeen);
        }
    }
}
