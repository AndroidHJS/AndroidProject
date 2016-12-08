package com.jack.comment.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public abstract  class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    public List<T> mData;
    public  Context mContext;
    public BaseRecyclerViewAdapter(List<T> data){
        this.mData=data;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        return getViewHolder(viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
          bindData(holder,mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public abstract BaseRecyclerViewHolder getViewHolder(int viewType);
    public abstract void  bindData(BaseRecyclerViewHolder holder, T t);
}
