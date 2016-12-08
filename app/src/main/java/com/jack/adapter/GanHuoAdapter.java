package com.jack.adapter;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jack.bean.GanHuoBean;
import com.jack.comment.base.BaseRecyclerViewAdapter;
import com.jack.comment.base.BaseRecyclerViewHolder;
import com.jack.main.R;
import com.jack.mymy.GanHuoDatailsActivity;

import java.util.List;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/7.
 */

public class GanHuoAdapter extends BaseRecyclerViewAdapter<GanHuoBean> {
    public GanHuoAdapter(List<GanHuoBean> data) {
        super(data);
    }

    @Override
    public BaseRecyclerViewHolder getViewHolder(int viewType) {
        View view=View.inflate(mContext,R.layout.item_ganhuo,null);
        return new GanHuoViewHolder(view) ;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, final GanHuoBean ganHuoBean) {
        if(holder instanceof GanHuoViewHolder){
             GanHuoViewHolder tempHolder= (GanHuoViewHolder) holder;
            String[] imgUrl=ganHuoBean.getImages();
            if(imgUrl!=null){
                tempHolder.setImage(imgUrl[0],tempHolder.mImgProject);
            }
            tempHolder.setText(ganHuoBean.getDesc(),tempHolder.mTvTitle);
            tempHolder.setText(ganHuoBean.getPublishedAt(),tempHolder.mTvTime);
            tempHolder.setText(ganHuoBean.getWho(),tempHolder.mTvName);
            tempHolder.mLayout_ganhuo.setOnClickListener(new View.OnClickListener() {
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
    public  void addData(List<GanHuoBean> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    static class GanHuoViewHolder extends BaseRecyclerViewHolder {
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
        public GanHuoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
