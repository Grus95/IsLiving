package com.example.grus95.baselibrary.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by grus95 on 16/8/31
 * 基础的组件类
 */
public abstract class BaseComponent {
    protected View root_view;
    protected Context mContext;
    protected Object data;


    protected Handler mHandler = new Handler(Looper.getMainLooper());

    public BaseComponent(Context context) {
        this.mContext = context;

        initFirst();

        int res_id = setContentView();
        if (res_id > 0) {
            root_view = LayoutInflater.from(context).inflate(setContentView(), null);
        }

        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
        initOther();
    }

    protected abstract void initFirst();

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initOther();

    public abstract void refresh();

    public abstract void release();

    public boolean isVisible() {
        return true;
    }

    public void setData(Object data) {
        this.data = data;
    }

    protected View findViewById(@IdRes int idRes) {
        if (null == root_view)
            throw new NullPointerException("root_view is null!!\tclass:" + this.getClass().getSimpleName());
        return root_view.findViewById(idRes);
    }


}
