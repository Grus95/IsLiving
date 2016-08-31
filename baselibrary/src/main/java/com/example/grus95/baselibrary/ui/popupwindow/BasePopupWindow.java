package com.example.grus95.baselibrary.ui.popupwindow;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class BasePopupWindow extends PopupWindow {

    protected Context mContext;
    protected View rootView;

    protected Handler mHandler = new Handler(Looper.getMainLooper());

    public BasePopupWindow(Context mContext) {
        super(mContext);
        this.mContext = mContext;

        initFirst();

        setContentView(rootView = setContentView());

        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
        initOther();
    }

    protected abstract View setContentView();

    protected abstract void initFirst();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initOther();


}
