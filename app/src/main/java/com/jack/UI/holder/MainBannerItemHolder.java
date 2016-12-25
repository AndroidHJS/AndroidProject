package com.jack.UI.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.bean.TopSotriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/24.
 */

public class MainBannerItemHolder extends BaseRecyclerViewHolder<TopSotriesBean>  {
    @Bind(R.id.img_banner_img)
    ImageView mBannerImg;
    @Bind(R.id.tv_banner_title)
    TextView mTvTitle;

    public MainBannerItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(TopSotriesBean topSotriesBean) {
         setImage(topSotriesBean.getImage(), mBannerImg);
         setText(topSotriesBean.getTitle(), mTvTitle);
    }
}

