package com.jack.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jack.AppApplication;
import com.jack.global.Constant;
import com.jack.main.R;
import com.jack.utils.ToastUtils;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity implements IWeiboHandler.Response,IUiListener{
    private IWeiboShareAPI mWeiboShareAPI ;
    private String title="";//标题
    private String description="";//描述
    private String url="";//分享链接
    private  Intent mIntent;
    private  Tencent mTencent;
    private IWXAPI mWXApi;
    private  int  mSharePlatform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bo_share);
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(),this);
        }
        initShareContent();
        share();
    }

    /***
     * 初始化分享内容
     */
    private void initShareContent() {
        mIntent = getIntent();
        title=mIntent.getStringExtra("title");
        description=mIntent.getStringExtra("description");
        url=mIntent.getStringExtra("url");
        mSharePlatform=mIntent.getIntExtra("Platform",0);
    }
    /***
     * 调用不同平台开始分享
     */
    private  void  share(){
        if(mSharePlatform==Constant.LoginPlatform.WeiBo){
            mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constant.AppKey.wei_bo_app_key);
            mWeiboShareAPI.registerApp();
            shareToWeibo(title, description,url);
        }else if(mSharePlatform==Constant.LoginPlatform.QQ){
            mTencent =Tencent.createInstance(Constant.AppKey.QQ_app_key,this);
            shareToQQ(title, description,url);
        }else if(mSharePlatform==Constant.LoginPlatform.WeChat) {
            mWXApi= WXAPIFactory.createWXAPI(this, Constant.AppKey.wechat_app_key, true);
            mWXApi.registerApp(Constant.AppKey.wechat_app_key);
            shareToWeChat(url,title, description);
        }
    }
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(mSharePlatform==Constant.LoginPlatform.WeiBo) {
            mWeiboShareAPI.handleWeiboResponse(intent,this);
        }else if(mSharePlatform==Constant.LoginPlatform.QQ){
            Tencent.handleResultData(intent,this);
        }
    }

    /**
     * 分享链接到QQ
     */
    public void shareToQQ(String ShareTitle, String ShareDescription, String Url){
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP );//类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, ShareTitle);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,ShareDescription);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,Url);
//        ArrayList<String> imageUrls = new ArrayList<>();
//        imageUrls.add("http://www.beehood.com/uploads/allimg/150310/2-150310142133.jpg");
//        params.putStringArrayList(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
          mTencent.shareToQQ(ShareActivity.this, params, this);
    }
     /**
      * 分享链接到微博
      */
    private void shareToWeibo(String ShareTitle, String ShareDescription, String Url) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = ShareTitle;
        mediaObject.description = ShareDescription;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl =Url;
        mediaObject.defaultText = "Webpage 默认文案";
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.mediaObject =mediaObject;
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        mWeiboShareAPI.sendRequest(ShareActivity.this, request);//唤起微博客户端回调接口
    }


    /**
      * 分享链接到微信
    */
    private void shareToWeChat(String  url, String title, String decs) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title =title;
        msg.description = decs;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        mWXApi.sendReq(req);
        finish();
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.handleResultData(data,this);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                ToastUtils.showToast("分享成功");
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                ToastUtils.showToast("分享取消");
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                ToastUtils.showToast("分享失败");
                break;
        }
        finish();

    }

    @Override
    public void onCancel() {
        ToastUtils.showToast("QQ 分享取消");
    }

    @Override
    public void onError(UiError e) {
        ToastUtils.showToast("QQ 分享出错");
    }

    @Override
    public void onComplete(Object response) {
        ToastUtils.showToast("QQ分享成功");
    }
}


