package com.jack.UI.holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.UI.NewDatailsActivity;
import com.jack.bean.StoriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;
import com.jack.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/24.
 */

public class NewHolder extends BaseRecyclerViewHolder<StoriesBean> {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.img_news_img)
    ImageView mImgNew;
    @Bind(R.id.layout_new)
    CardView mLayoutNew;
    public NewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(StoriesBean storiesBean) {
        setText(storiesBean.getTitle(),mTvTitle);
        setVisibility(mImgNew);
        String[]  imgUrls=storiesBean.getImages();
        if(imgUrls==null||imgUrls.length==0){
            setGone(mImgNew);
        }else{
            setVisibility(mImgNew);
            setImage(imgUrls[0].trim(),mImgNew);
        }
        mLayoutNew.setTag(storiesBean);
    }
    @OnClick(R.id.layout_new)
    public  void enterNewDatails(View view){
        ToastUtils.showToast("点击新闻");
        Object tempObj = view.getTag();
        if(tempObj !=null&&tempObj instanceof StoriesBean){
            Intent intent=new Intent();
            intent.setClass(mContext, NewDatailsActivity.class);
            intent.putExtra("newItem",(StoriesBean)tempObj);
            mContext.startActivity(intent);
        }

    }
}
