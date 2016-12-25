package com.jack.UI.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.bean.GanHuoBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;
import com.jack.UI.GanHuoDatailsActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/25.
 */

public class GanHuoHolder extends BaseRecyclerViewHolder<GanHuoBean> {
        @Bind(R.id.tv_project_decs)
        TextView mTvTitle;
        @Bind(R.id.img_project_img)
        ImageView mImgProject;
        @Bind(R.id.tv_project_who)
         TextView mTvName;
        @Bind(R.id.tv_project_time)
         TextView mTvTime;
        @Bind(R.id.layout_ganhuo)
        LinearLayout mLayout_ganhuo;
    public GanHuoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(final  GanHuoBean ganHuoBean) {
        String[] imgUrl=ganHuoBean.getImages();
        if(imgUrl!=null){
            setImage(imgUrl[0],mImgProject);
        }
        setText(ganHuoBean.getDesc(),mTvTitle);
        setText(ganHuoBean.getPublishedAt(),mTvTime);
        setText(ganHuoBean.getWho(),mTvName);
        mLayout_ganhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("url",ganHuoBean.getUrl());
                intent.setClass(mContext, GanHuoDatailsActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
