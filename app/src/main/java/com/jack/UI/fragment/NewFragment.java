package com.jack.UI.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jack.Net.RetrofitHelper;
import com.jack.UI.holder.MainBannerHolder;
import com.jack.UI.holder.NewestNewHolder;
import com.jack.bean.New;
import com.jack.bean.StoriesBean;
import com.jack.bean.TopSotriesBean;
import com.jack.UI.base.BaseFragment;
import com.jack.main.R;
import com.jack.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/4.
 */

public class NewFragment extends BaseFragment<New> {
    @Bind(R.id.layout_refresh)
    SwipeRefreshLayout  mRefreshLayout;
    private View mView;
    private MainBannerHolder mMainBannerHolder;
    private NewestNewHolder mNewstNewHolder;
    @Bind(R.id.layout_new_container)
    LinearLayout mLayoutNewContainer;
    private  boolean mIsRefresh=false;

    @Override
    public View createSuccessView( ViewGroup container) {
        mView=View.inflate(mActivity,R.layout.fragment_new,container);
        return mView;
    }

    @Override
    public void initData() {
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public Observable<New> getObservable() {
        return RetrofitHelper.getNewLatestList();
    }

    @Override
    public void bindData(New news) {
        if(news==null){
            return;
        }
        if(mIsRefresh){
            mLayoutNewContainer.removeAllViews();
            mRefreshLayout.setRefreshing(false);
        }
        mLayoutNewContainer.removeAllViews();
        List<TopSotriesBean> topSotriesBeans=news.getTop_stories();
        List<StoriesBean> storiesBeen=news.getStories();
        View MainBannerView=LayoutInflater.from(mActivity).inflate(R.layout.holder_main_banner,mLayoutNewContainer);
        mMainBannerHolder=new MainBannerHolder(MainBannerView);
        View  NewstNewView=LayoutInflater.from(mActivity).inflate(R.layout.holder_newest_new,mLayoutNewContainer);
        mNewstNewHolder=new NewestNewHolder(NewstNewView);
        mMainBannerHolder.bindData(topSotriesBeans);
        mNewstNewHolder.bindData(storiesBeen);
        mIsRefresh=false;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mIsRefresh=true;
    }
}
