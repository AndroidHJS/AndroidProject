package com.jack.UI;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.jack.UI.base.BaseActivity;
import com.jack.View.LoadingView;
import com.jack.main.R;

import butterknife.Bind;

import static com.jack.main.R.id.toolbar_datails;

/***
 * 还存在些问题
 */
public class GanHuoDatailsActivity extends BaseActivity {
    @Bind(R.id.webView_ganghuo)
     WebView mWebView;
    private  String mURL;
    @Bind(toolbar_datails)
    Toolbar mToolbar;
    private String mWho;
    @Bind(R.id.layout_net_error)
    View mLayoutNetError;
    @Bind(R.id.layout_loading)
    View mLayoutLoading;
    @Override
    public void initData() {
        mLayoutLoading.setVisibility(View.VISIBLE);
        mWebView.loadUrl(mURL);
        mWebView.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                 mLayoutLoading.setVisibility(View.GONE);
//                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
               mLayoutNetError.setVisibility(View.VISIBLE);
               mLayoutLoading.setVisibility(View.GONE);
            }
        });
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
