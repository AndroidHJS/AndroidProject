package com.jack.UI.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.bean.StoriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/24.
 */

public class NewHolder extends BaseRecyclerViewHolder<StoriesBean> {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.img_news_img)
    ImageView mImgNew;
    public NewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(StoriesBean storiesBean) {
        setText(storiesBean.getTitle(),mTvTitle);
        setImage(storiesBean.getImages()[0],mImgNew);
    }
}
