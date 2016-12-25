package com.jack.UI.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.jack.bean.StoriesBean;
import com.jack.UI.base.BaseRecyclerViewHolder;
import com.jack.main.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/24.
 */

public class NewestNewHolder extends BaseRecyclerViewHolder<List<StoriesBean>> {
    @Bind(R.id.layout_story)
     LinearLayout mLayoutStory;
    public NewestNewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(List<StoriesBean> storiesBeen) {
        if(storiesBeen==null||storiesBeen.size()==0){
            return;
        }
        for (StoriesBean story:storiesBeen  ) {
            View  view=View.inflate(mContext,R.layout.item_new,null);
//             View view= LayoutInflater.from(mContext).inflate(R.layout.item_new,mLayoutStory);
             mLayoutStory.addView(view);
             NewHolder   NewHolder=new NewHolder(view);
             NewHolder.bindData(story);

        }


    }
}
