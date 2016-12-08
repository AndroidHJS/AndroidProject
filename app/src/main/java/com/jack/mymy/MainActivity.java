package com.jack.mymy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jack.adapter.MainAdapter;
import com.jack.comment.base.BaseActivity;
import com.jack.comment.base.BaseFragment;
import com.jack.fragment.GanioFragment;
import com.jack.fragment.MeiZiFragment;
import com.jack.fragment.NewFragment;
import com.jack.main.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tabs)
    TabLayout mMainTayLayout;
    @Bind(R.id.viewpager)
    ViewPager mMainViewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private MainAdapter mMainAdapter;
    private String[] mTabs=new String[]{
            "新闻","干货","美女"
    };
    private BaseFragment[] mFragment=new BaseFragment[] {
            new NewFragment(),new GanioFragment(), new  MeiZiFragment(),
    };
    private FragmentManager mFragmentManager;
    @Override
    public void initData() {

    }
    @Override
    public void init(){
        mFragmentManager=this.getSupportFragmentManager();
        setSupportActionBar(mToolBar);
        mMainAdapter= new MainAdapter(mFragmentManager,mTabs,mFragment);
        mMainViewPager.setAdapter(mMainAdapter);
        mMainTayLayout.setupWithViewPager(mMainViewPager);
        mMainTayLayout.setTabMode(TabLayout.MODE_FIXED);

    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
