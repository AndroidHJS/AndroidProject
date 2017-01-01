package com.jack.UI;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.bean.NewDetaiBean;
import com.jack.bean.StoriesBean;
import com.jack.UI.base.BaseActivity;
import com.jack.main.R;
import com.jack.utils.RxJavaUtils;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import rx.functions.Action1;

public class NewDatailsActivity extends BaseActivity {
    @Bind(R.id.webView_new_Dataisl)
    WebView mWebView;
    @Bind(R.id.img_news_datails)
    ImageView mImgDatailsNew;
    private StoriesBean mStroiesBean;
    @Bind(R.id.toolbar_datails)
    Toolbar mToolbarDatails;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolBarLayout;
    @Bind(R.id.layout_collapsing)
    FrameLayout mLayoutCollapsing;
    @Override
    public  void initData(){
        if(mStroiesBean.getImages()!=null){
            mLayoutCollapsing.setVisibility(View.VISIBLE);
            Glide.with(this).load(mStroiesBean.getImages()[0]).into(mImgDatailsNew);
        }else{
            mLayoutCollapsing.setVisibility(View.GONE);
        }
        mCollapsingToolBarLayout.setTitle(mStroiesBean.getTitle());
        mToolbarDatails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDatailsActivity.this.finish();
            }
        });
        RetrofitHelper.getNewDatails(mStroiesBean.getId()).compose(RxJavaUtils.<NewDetaiBean>applySchedulers()).subscribe(new Action1<NewDetaiBean>() {
            @Override
            public void call(NewDetaiBean s) {
                  mWebView.loadDataWithBaseURL(null, s.getBody(), "text/html", "utf-8", null);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println(throwable.toString());
            }
        });
    }

    @Override
    public void init() {
        this.setSupportActionBar(mToolbarDatails);
        mStroiesBean = (StoriesBean) getIntent().getSerializableExtra("newItem");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_datails;
    }
}
