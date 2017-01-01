package com.jack.UI.fragment;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jack.Net.RetrofitHelper;
import com.jack.UI.adapter.MeiZhiAdapter;
import com.jack.UI.base.BaseFragment;
import com.jack.View.GalleryView.CardScaleHelper;
import com.jack.View.GalleryView.SpeedRecyclerView;
import com.jack.bean.MeiZhi;
import com.jack.bean.MeiZhiBean;
import com.jack.main.R;
import com.jack.utils.ViewSwitchUtils;
import com.vansuita.gaussianblur.GaussianBlur;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/4.
 */

public class MeiZiFragment extends BaseFragment<MeiZhi> {
     @Bind(R.id.RecyclerView_meizhi)
     SpeedRecyclerView mXRecyclerView;
    private MeiZhiAdapter mAdapter;
    private static final int PAGE_NUM=20;
    private     int PAGE=1;
    private  boolean mIsRefresh=false;
    private  View mView;
    @Bind(R.id.blurView)
     ImageView mBlurView;
    private CardScaleHelper mCardScaleHelper = null;
    private Runnable mBlurRunnable;
    private int mLastPos = -1;
    private List<MeiZhiBean> mMeiZhiData;
    private Handler  mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mCardScaleHelper.attachToRecyclerView(mXRecyclerView);
            initBlurBackground();
        }
    };
    @Override
    public View createSuccessView( ViewGroup container) {
         mView=View.inflate(mActivity,R.layout.fragment_meizi,container);
         return mView;
    }
    @Override
    public void initData() {
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mXRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter=new MeiZhiAdapter(new ArrayList<MeiZhiBean>());
        mXRecyclerView.setAdapter(mAdapter);
        mCardScaleHelper = new CardScaleHelper();
    }

    @Override
    public void bindData(MeiZhi meiZhi) {
        mMeiZhiData = meiZhi.getResults();
        mAdapter.addData(mMeiZhiData,mIsRefresh);
        mHandler.sendEmptyMessage(1);
    }


    @Override
    public Observable<MeiZhi> getObservable() {
        return RetrofitHelper.getMeiZhiList(PAGE_NUM,PAGE);
    }
    private void initBlurBackground() {
        // mRecyclerView绑定scale效果
        mXRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange();
                }
            }
});
        notifyBackgroundChange();
        }

    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()){
            return;
        }
        mLastPos = mCardScaleHelper.getCurrentItemPos();
        if(mMeiZhiData ==null){
            return;
        }
        final String  imgUrl = mMeiZhiData.get(mCardScaleHelper.getCurrentItemPos()).getUrl();
        mBlurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                Glide.with(mActivity).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap scaledDownBitmap = GaussianBlur.with(mActivity).maxSixe(10).scaleDown(resource);
                        ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView,scaledDownBitmap );
                    }
                });
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 500);
    }

}
