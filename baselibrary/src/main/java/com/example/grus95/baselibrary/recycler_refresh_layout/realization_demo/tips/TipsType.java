package com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.tips;

import android.content.Context;

import com.example.grus95.baselibrary.R;


/**
 *  Created by grus95 on 16/8/31
 */
public enum TipsType {

  LOADING(R.layout.tips_loading),
  LOADING_FAILED(R.layout.tips_loading_failed),
  EMPTY(R.layout.tips_empty);

  protected int mLayoutRes;

  TipsType(int layoutRes) {
    this.mLayoutRes = layoutRes;
  }

  protected Tips createTips(Context context) {
    return new Tips(context, mLayoutRes);
  }

}
