package com.jack.mymy;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.bean.StoriesBean;
import com.jack.comment.base.BaseActivity;
import com.jack.main.R;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;

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
    @Override
    public  void initData(){
        Glide.with(this).load(mStroiesBean.getImages()[0]).into(mImgDatailsNew);
        mCollapsingToolBarLayout.setTitle(mStroiesBean.getTitle());
        mToolbarDatails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDatailsActivity.this.finish();
            }
        });
        RetrofitHelper.getNewDatails(new RequestListener() {
            @Override
            public void onSuccess(Object obj) {
                if(obj instanceof  String){
                    String JSONStr= (String) obj;
                    try {
                        JSONObject  jsonObject=new JSONObject(JSONStr);
                        mWebView.loadDataWithBaseURL(null, jsonObject.optString("body"), "text/html", "utf-8", null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFail(Throwable throwable) {


            }
        },mStroiesBean.getId());

    }

    @Override
    public void init() {
        this.setSupportActionBar(mToolbarDatails);
        mStroiesBean= (StoriesBean) getIntent().getSerializableExtra("newItem");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_datails;
    }
}
