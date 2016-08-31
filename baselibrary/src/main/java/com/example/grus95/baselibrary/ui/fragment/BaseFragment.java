package com.example.grus95.baselibrary.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class BaseFragment extends Fragment {

    protected Handler mHandle = new Handler(Looper.getMainLooper());

    protected View root_view;

    public BaseFragment() {
        super();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOnCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int res_id = setContentView();
        if (res_id > 0) {
            root_view = inflater.inflate(setContentView(), null);
        }
        return root_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        initView();
        initData();
        initListener();
        initOther();
    }

    protected abstract void initOnCreate(Bundle savedInstanceState);

    protected abstract void initActivityCreated(Bundle savedInstanceState);

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initOther();


    protected View findViewById(@IdRes int id) {
        if (null != root_view) {
            return root_view.findViewById(id);
        } else {
            throw new RuntimeException("活捉一fragment中的root_view为null，该fragment是：" + this.getClass().getName());
        }
    }
}
