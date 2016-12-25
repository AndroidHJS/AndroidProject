package com.jack.UI.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.main.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */

public  abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    public Context mContext;
    public   View mView;
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.mContext=itemView.getContext();
        this.mView=itemView;
        ButterKnife.bind(this,itemView);
    }
    public  void setImage(String url, ImageView img){
        Glide.with(mContext).load(url).placeholder(R.mipmap.ic_launcher).into(img);
    }
    public  void  setText(String test, TextView  textView){
        textView.setText(test);
    }
    public abstract void  bindData(T t);
    public  View getView(){
        return mView;
    }


}
