package com.jack.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jack.Net.RequestListener;
import com.jack.Net.RetrofitHelper;
import com.jack.bean.GanIO;
import com.jack.comment.base.BaseFragment;
import com.jack.main.R;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/4.
 */

public class GanioFragment extends BaseFragment {


    @Override
    public View getView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_ganio,null);
        return view;
    }

    @Override
    public void initData() {
        RetrofitHelper.getGanHuoInfo(new RequestListener() {
            @Override
            public void onSuccess(Object obj) {
                if(obj instanceof GanIO){
                    GanIO ganHuo= (GanIO) obj;
                    Toast.makeText(mActiviy,ganHuo.getResults().get(0).getDesc(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFail(Throwable throwable) {

            }
        },20,1, GanIO.class);

    }

}
