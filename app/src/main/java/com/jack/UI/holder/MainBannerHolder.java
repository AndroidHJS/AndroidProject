package com.jack.UI.holder;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jack.UI.adapter.MainBannerAdapter;
import com.jack.bean.TopSotriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/24.
 */

public class MainBannerHolder extends BaseRecyclerViewHolder<List<TopSotriesBean>>{
    private MainBannerAdapter mMainBannerAdapter;
    @Bind(R.id.viewpager_banner)
    ViewPager mViewPagerBanner;
    private static int mCurrentPos =1;
    private  int mBannerSize=0;
    public MainBannerHolder(View itemView) {
        super(itemView);
    }
    private Subscription mViewPagerSubscribe;
    @Override
    public void bindData(List<TopSotriesBean> topSotriesBeen) {
        if(topSotriesBeen==null||topSotriesBeen.size()==0){
            return;
        }
        mBannerSize=topSotriesBeen.size();
        mMainBannerAdapter=new MainBannerAdapter(topSotriesBeen,mContext);
        mViewPagerBanner.setAdapter(mMainBannerAdapter);
        start();
        mViewPagerBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        stop();
                        break;
                    case MotionEvent.ACTION_UP:
                        start();
                        break;
                }
                return false;
            }
        });
        mViewPagerBanner.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPos=position;
            }
        });

    }
    private  void  start(){
         mViewPagerSubscribe = Observable.interval(5, 5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                mViewPagerBanner.setCurrentItem(mCurrentPos);
                mCurrentPos++;
                if (mCurrentPos == mBannerSize) {
                    mCurrentPos = 0;
                }
            }
        });

    }
    private  void stop(){
        if(!mViewPagerSubscribe.isUnsubscribed()) {
            mViewPagerSubscribe.unsubscribe();
        }
    }

}
