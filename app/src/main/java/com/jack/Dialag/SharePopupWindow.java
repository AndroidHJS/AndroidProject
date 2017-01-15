package com.jack.Dialag;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jack.main.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack on 2017/1/10.
 */

public class SharePopupWindow extends PopupWindow {
    private Context mContext;
    @Bind(R.id.btn_weibo_share)
    ImageButton mBtnWeiboShare;
    @Bind(R.id.btn_qq_share)
    ImageButton mBtnQQshare;
    private View mView;
    private ShareListener mShareListener;
    public  SharePopupWindow(Context context){
        this.mContext=context;
        mView= LayoutInflater.from(context).inflate(R.layout.dialog_share,null);
        init();
    }
    private  void  init(){
        setContentView(mView);
        ButterKnife.bind(this,mView);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.share_anim);
    }
    @OnClick({R.id.btn_weibo_share,R.id.btn_qq_share,R.id.btn_wechat_share})
    public   void  Share(View  view){
        if (mShareListener != null) {
            mShareListener.share(view);
        }
    }
    public  void  setShareListener(ShareListener ShareListener){
        this.mShareListener=ShareListener;

    }
   public   interface   ShareListener{
        void  share(View view);
    }

}
