package com.jack.UI.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jack.bean.GanHuoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public abstract  class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    public List<T> mData;
    public  Context mContext;
    public  ViewGroup mParent;
    public LayoutInflater  mInflater;
    public BaseRecyclerViewAdapter(List<T> data){
        this.mData=data;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        mParent=parent;
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
    public  void addData(List<T> data, boolean IsRefresh){
        if(IsRefresh){
            mData.clear();

        }
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
