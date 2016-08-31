package com.example.grus95.baselibrary.common;

import android.content.Context;
import android.util.Log;

import com.example.grus95.baselibrary.BaseApplication;

import java.lang.reflect.Field;


/**
 * Created by grus95 on 16/8/31
 */
public class Res {
    private static final String TAG = Res.class.getName();
    private static Res self;

    private Context mContext;

    private static Class<?> CDrawable = null;

    private static Class<?> CLayout = null;

    private static Class<?> CId = null;

    private static Class<?> CAnim = null;

    private static Class<?> CStyle = null;

    private static Class<?> CString = null;

    private static Class<?> CArray = null;

    private static Class<?> CMipmap = null;

    public static Res get() {
        if (self == null) {
            self = new Res(BaseApplication.getApp());
        }
        return self;
    }

    private Res(Context context) {
        this.mContext = context.getApplicationContext();
        try {
            CDrawable = Class.forName(this.mContext.getPackageName()
                    + ".R$drawable");
            CMipmap = Class.forName(this.mContext.getPackageName() + ".R$mipmap");
            CLayout = Class.forName(this.mContext.getPackageName()
                    + ".R$layout");
            CId = Class.forName(this.mContext.getPackageName() + ".R$id");
            CAnim = Class.forName(this.mContext.getPackageName() + ".R$anim");
            CStyle = Class.forName(this.mContext.getPackageName() + ".R$style");
            CString = Class.forName(this.mContext.getPackageName()
                    + ".R$string");
            CArray = Class.forName(this.mContext.getPackageName() + ".R$array");

        } catch (ClassNotFoundException e) {
            Log.i(TAG, e.getMessage());
        }
    }

    public int getDrawableId(String resName) {
        return getResId(CDrawable, resName);
    }

    public int getMipmapId(String resName){
        return getResId(CMipmap,resName);
    }

    public int getLayoutId(String resName) {
        return getResId(CLayout, resName);
    }

    public int getIdId(String resName) {
        return getResId(CId, resName);
    }

    public int getAnimId(String resName) {
        return getResId(CAnim, resName);
    }

    public int getStyleId(String resName) {
        return getResId(CStyle, resName);
    }

    public int getStringId(String resName) {
        return getResId(CString, resName);
    }

    public int getArrayId(String resName) {
        return getResId(CArray, resName);
    }



    private int getResId(Class<?> resClass, String resName) {
        if (resClass == null) {
            Log.d(TAG, "getRes("+resClass+"," + resName + ")");
            throw new IllegalArgumentException(
                    "ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have "
                            + this.mContext.getPackageName()
                            + ".R$* configured in obfuscation. field="
                            + resName);
        }

        try {
            Field field = resClass.getField(resName);
            return field.getInt(resName);
        } catch (Exception e) {
            Log.i(TAG, "getRes(" + resClass.getName() + ", " + resName + ")");
            Log.i(TAG,
                    "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");

            Log.i(TAG, e.getMessage());
        }

        return -1;
    }
}