package com.example.grus95.utilslibrary.shared_preferences.sp_impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.grus95.utilslibrary.log.DLog;
import com.example.grus95.utilslibrary.shared_preferences.sp_interface.SPEngine;

import java.util.Map;
import java.util.Set;



/**
 * Created by grus95 on 16/8/31
 */
public class SPAndroidEngine implements SPEngine {

    private SharedPreferences mSharedPreferences;


    @Override
    public void init(Context context, String name, int mode) {
        mSharedPreferences = context.getSharedPreferences(name, mode);
    }

    @Override
    public boolean isInit() {
        if (null != mSharedPreferences) {
            return true;
        }else {
            DLog.get().e("sp init error!!!");
            throw new IllegalStateException("Hi,your forget to init SP。\nclass:" + super.getClass().getSimpleName());
        }
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        isInit();
        return mSharedPreferences;
    }

    @Override
    public SharedPreferences.Editor getEdit() {
        return getSharedPreferences().edit();
    }

    @Override
    public SPEngine putString(String key, @Nullable String value) {
        getEdit().putString(key, value);
        return this;
    }

    @Override
    public SPEngine putInt(String key, int value) {
        getEdit().putInt(key, value);
        return this;
    }

    @Override
    public SPEngine putBoolean(String key, boolean value) {
        getEdit().putBoolean(key, value);
        return this;
    }

    @Override
    public SPEngine putFloat(String key, float value) {
        getEdit().putFloat(key, value);
        return this;
    }

    @Override
    public SPEngine putLong(String key, long value) {
        getEdit().putLong(key, value);
        return this;
    }

    @Override
    public SPEngine putStringSet(String key, @Nullable Set<String> values) {
        getEdit().putStringSet(key, values);
        return this;
    }

    @Override
    public void commit() {
        getEdit().commit();
    }

    @Override
    public Map<String, ?> getAll() {
        return getSharedPreferences().getAll();
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return getSharedPreferences().getFloat(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return getSharedPreferences().getStringSet(key, defValues);
    }

    @Override
    public boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    @Override
    public SPEngine remove(String key) {
        getEdit().remove(key);
        return this;
    }

    @Override
    public SPEngine clear() {
        getEdit().clear();
        return this;
    }
}
