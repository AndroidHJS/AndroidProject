package com.jack.UI.adapter;

import android.view.View;

import com.jack.UI.holder.MeiZhiHolder;
import com.jack.bean.MeiZhiBean;
import com.jack.UI.base.BaseRecyclerViewAdapter;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MeiZhiAdapter extends BaseRecyclerViewAdapter<MeiZhiBean> {

    public MeiZhiAdapter(List<MeiZhiBean> data) {
        super(data);
    }
    @Override
    public BaseRecyclerViewHolder getViewHolder(int viewType) {
        return new MeiZhiHolder(View.inflate(mContext, R.layout.item_meizhi,null));
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, MeiZhiBean meiZhiBean) {
        if(holder instanceof MeiZhiHolder){
           ((MeiZhiHolder) holder).bindData(meiZhiBean);
        }

    }

}
