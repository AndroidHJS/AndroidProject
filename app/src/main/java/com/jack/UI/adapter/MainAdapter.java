package com.jack.UI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jack.UI.base.BaseFragment;

/**
 * Created by Administrator on 2016/12/4.
 */

public class MainAdapter extends FragmentPagerAdapter{
    public  String[] mTab;
    private BaseFragment[] mMainFragment;
    public MainAdapter(FragmentManager fm,String[] Tab,BaseFragment[] MainFragment) {
        super(fm);
        this.mMainFragment=MainFragment;
        this.mTab=Tab;
    }

    @Override
    public Fragment getItem(int position) {
        return mMainFragment[position];
    }

    @Override
    public int getCount() {
        return mMainFragment.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
