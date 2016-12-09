package com.jack.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.bean.MeiZhiBean;
import com.jack.comment.base.BaseRecyclerViewAdapter;
import com.jack.comment.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

import butterknife.Bind;

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
            MeiZhiHolder tempHolder= (MeiZhiHolder) holder;
            tempHolder.setImage(meiZhiBean.getUrl(),tempHolder.mImgMeiZhi);
            tempHolder.setText(meiZhiBean.getDesc(),tempHolder.mTvName);
        }

    }

    static  class MeiZhiHolder extends BaseRecyclerViewHolder{
        @Bind(R.id.img_meizhi)
        ImageView mImgMeiZhi;
        @Bind(R.id.tv_meizhi_name)
        TextView mTvName;
        public MeiZhiHolder(View itemView) {
            super(itemView);
        }
    }
    public  void  addData(List<MeiZhiBean> data,boolean refresh){
        if(refresh){
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
