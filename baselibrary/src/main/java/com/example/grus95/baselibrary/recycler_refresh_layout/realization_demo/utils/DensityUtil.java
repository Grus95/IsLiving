package com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.utils;

import android.content.Context;

/**
 * Created by grus95 on 16/8/31
 */
public class DensityUtil {

    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }  
}