package com.jack.UI.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.bean.MeiZhiBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/25.
 */

public class MeiZhiHolder  extends BaseRecyclerViewHolder<MeiZhiBean>{
    @Bind(R.id.img_meizhi)
    ImageView mImgMeiZhi;
    public MeiZhiHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(MeiZhiBean meiZhiBean) {
         setImage(meiZhiBean.getUrl(),mImgMeiZhi);

    }
}
