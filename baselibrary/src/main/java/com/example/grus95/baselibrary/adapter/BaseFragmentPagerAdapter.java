package com.example.grus95.baselibrary.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
}
