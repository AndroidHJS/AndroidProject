package com.jack.comment.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        mContext=itemView.getContext();
        ButterKnife.bind(this,itemView);
    }
    public  void setImage(String url, ImageView img){
        Glide.with(mContext).load(url).into(img);
    }
    public  void  setText(String test, TextView  textView){
        textView.setText(test);
    }
}
