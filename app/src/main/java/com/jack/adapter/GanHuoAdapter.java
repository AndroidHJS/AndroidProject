package com.jack.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.bean.GanHuoBean;
import com.jack.main.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/7.
 */

public class GanHuoAdapter  extends RecyclerView.Adapter<GanHuoAdapter.GanHuoViewHolder> {
    private Context mContext;
    private List<GanHuoBean> mData;
    public  GanHuoAdapter(Context Context, List<GanHuoBean> mData){
        this.mContext=Context;
        this.mData=mData;
    }

    @Override
    public GanHuoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view=View.inflate(mContext, R.layout.item_ganhuo,null);
        GanHuoViewHolder holder=new GanHuoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GanHuoViewHolder holder, int position) {
        GanHuoBean ganHuoBean = mData.get(position);
        holder.mTvTitle.setText(ganHuoBean.getDesc());
        if(ganHuoBean.getImages()!=null){
            Glide.with(mContext).load(ganHuoBean.getImages()[0]).into(holder.mImgProject);
        }
        holder.mTvTime.setText(ganHuoBean.getCreatedAt());
        holder.mTvName.setText(ganHuoBean.getWho());


    }
   public  void addData( List<GanHuoBean> data){
       this.mData.addAll(data);
       this.notifyDataSetChanged();

   }

    @Override
    public int getItemCount() {
        return mData.size();
    }

   static class GanHuoViewHolder extends RecyclerView.ViewHolder{
         @Bind(R.id.tv_project_decs)
         TextView mTvTitle;
        @Bind(R.id.img_project_img)
         ImageView mImgProject;
        @Bind(R.id.tv_project_who)
         TextView mTvName;
        @Bind(R.id.tv_project_time)
         TextView mTvTime;
        public GanHuoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
