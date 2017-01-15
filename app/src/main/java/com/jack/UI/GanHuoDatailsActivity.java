package com.jack.UI;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jack.Dialag.SharePopupWindow;
import com.jack.UI.base.BaseActivity;
import com.jack.global.Constant;
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
    @Bind(R.id.layout_net_error)
    View mLayoutNetError;
    @Bind(R.id.layout_loading)
    View mLayoutLoading;
    private SharePopupWindow mShareView;
    private  int  mSharePlatForm= Constant.LoginPlatform.QQ;
    private  Toolbar.OnMenuItemClickListener mMenuItemListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_settings:
                    showToast("设置");
                    break;
                case R.id.action_share:
                    showPopFormBottom();
                    break;
                case R.id.action_aboutMe:
                    showToast("关于我");
                    break;
            }
            return false;
        }
    };
    @Override
    public void initData() {
        mLayoutLoading.setVisibility(View.VISIBLE);
        mWebView.loadUrl(mURL);
        mToolbar.setOnMenuItemClickListener(mMenuItemListener);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                 mLayoutLoading.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
               mLayoutNetError.setVisibility(View.VISIBLE);
               mLayoutLoading.setVisibility(View.GONE);
            }
        });
        mShareView.setShareListener(new SharePopupWindow.ShareListener() {
            @Override
            public void share(View view) {
                mShareView.dismiss();
                switch (view.getId()){
                    case R.id.btn_weibo_share:
                        mSharePlatForm=Constant.LoginPlatform.WeiBo;
                        break;
                    case R.id.btn_qq_share:
                        mSharePlatForm=Constant.LoginPlatform.QQ;
                        break;
                    case R.id.btn_wechat_share:
                        mSharePlatForm=Constant.LoginPlatform.WeChat;
                        break;
                }
                Intent  intent=new Intent();
                intent.setClass(GanHuoDatailsActivity.this,ShareActivity.class);
                intent.putExtra("url",mURL);
                intent.putExtra("title",mWebView.getTitle());
                intent.putExtra("description","Test");
                intent.putExtra("Platform",mSharePlatForm);
                startActivity(intent);
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
        mShareView=new SharePopupWindow(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gan_huo_datails;
    }
    private   void showPopFormBottom() {
        mShareView.showAtLocation(findViewById(R.id.webView_ganghuo), Gravity.CENTER, 0, 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return true;
    }
}
