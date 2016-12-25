package com.jack.UI.adapter;

import android.view.View;

import com.jack.UI.holder.GanHuoHolder;
import com.jack.bean.GanHuoBean;
import com.jack.UI.base.BaseRecyclerViewAdapter;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

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
        GanHuoHolder  holder=new GanHuoHolder(view);
        return holder ;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, final GanHuoBean ganHuoBean) {
        if(holder instanceof GanHuoHolder){
            ((GanHuoHolder) holder).bindData(ganHuoBean);
        }
    }


}
