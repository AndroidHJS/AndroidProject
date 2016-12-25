package com.jack.UI;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.jack.UI.base.BaseActivity;
import com.jack.main.R;

import butterknife.Bind;

import static com.jack.main.R.id.toolbar_datails;

public class GanHuoDatailsActivity extends BaseActivity {
    @Bind(R.id.webView_ganghuo)
     WebView mWebView;
    private  String mURL;
    @Bind(toolbar_datails)
    Toolbar mToolbar;
    private String mWho;
    @Override
    public void initData() {
        mWebView.loadUrl(mURL);
    }

    @Override
    public void init() {
        mURL=getIntent().getStringExtra("url");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GanHuoDatailsActivity.this.finish();
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gan_huo_datails;
    }
}
