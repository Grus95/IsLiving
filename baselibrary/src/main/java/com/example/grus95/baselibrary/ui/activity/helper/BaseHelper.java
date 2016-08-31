package com.example.grus95.baselibrary.ui.activity.helper;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class BaseHelper {

    protected View root_view;

    public void init() {
        initFirst();
        initView();
        initData();
        initListener();
        initOther();
    }


    protected abstract void initFirst();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initOther();


    protected View findViewById(@IdRes int id) {
        if (null != root_view) {
            return root_view.findViewById(id);
        } else {
            throw new RuntimeException("活捉一helper中的root_view为null，该helper是：" + this.getClass().getName());
        }
    }
}
