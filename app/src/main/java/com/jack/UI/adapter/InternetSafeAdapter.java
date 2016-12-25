package com.jack.UI.adapter;


import android.view.LayoutInflater;
import android.view.View;

import com.jack.UI.holder.NewHolder;
import com.jack.bean.StoriesBean;
import com.jack.UI.base.BaseRecyclerViewAdapter;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */

public class InternetSafeAdapter extends BaseRecyclerViewAdapter<StoriesBean> {
    public InternetSafeAdapter(List data) {
        super(data);
    }

    @Override
    public BaseRecyclerViewHolder getViewHolder(int viewType) {
        View view=LayoutInflater.from(mContext).inflate( R.layout.item_new,mParent,false);
        NewHolder  mNewHolder=new NewHolder(view);
        return mNewHolder;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, StoriesBean storiesBean) {
       if(holder instanceof  NewHolder){
           ((NewHolder) holder).bindData(storiesBean);
       }

    }


}
