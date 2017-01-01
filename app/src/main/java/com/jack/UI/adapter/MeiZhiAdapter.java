package com.jack.UI.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jack.UI.holder.MeiZhiHolder;
import com.jack.View.GalleryView.CardAdapterHelper;
import com.jack.bean.MeiZhiBean;
import com.jack.UI.base.BaseRecyclerViewAdapter;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;
import com.jack.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MeiZhiAdapter extends BaseRecyclerViewAdapter<MeiZhiBean> {
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();
    public MeiZhiAdapter(List<MeiZhiBean> data) {
        super(data);
    }
    @Override
    public BaseRecyclerViewHolder getViewHolder(int viewType) {
        View itemView = LayoutInflater.from(mParent.getContext()).inflate(R.layout.item_meizhi, mParent, false);
        mCardAdapterHelper.onCreateViewHolder(mParent, itemView);
        return new MeiZhiHolder(itemView);
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, MeiZhiBean meiZhiBean) {
          Log.i("Test",meiZhiBean.toString());
         mCardAdapterHelper.onBindViewHolder(holder.itemView, mData.indexOf(meiZhiBean), getItemCount());
        if(holder instanceof MeiZhiHolder){
           ((MeiZhiHolder) holder).bindData(meiZhiBean);
        }

    }

}
