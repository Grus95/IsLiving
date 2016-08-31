package com.example.grus95.utilslibrary.shared_preferences.sp_interface;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by grus95 on 16/8/31
 */
public interface SPEngine {

    /**
     * 初始化
     *
     * @param context
     */
    void init(Context context, String name,
              int mode);


    boolean isInit();


    //================================save start===========================//

    /**
     * 获取一个SP
     *
     * @return
     */
    SharedPreferences getSharedPreferences();

    /**
     * 获取一个Edit
     *
     * @return
     */
    SharedPreferences.Editor getEdit();

    /**
     * 保存一个String
     *
     * @param key
     * @param value
     * @return
     */
    SPEngine putString(String key, @Nullable String value);

    /**
     * 保存一个Int
     *
     * @param key
     * @param value
     * @return
     */
    SPEngine putInt(String key, int value);

    /**
     * 保存一个Boolean
     *
     * @param key
     * @param value
     * @return
     */
    SPEngine putBoolean(String key, boolean value);

    /**
     * 保存一个Float
     *
     * @param key
     * @param value
     * @return
     */
    SPEngine putFloat(String key, float value);

    /**
     * 保存一个Long
     *
     * @param key
     * @param value
     * @return
     */
    SPEngine putLong(String key, long value);

    /**
     * 保存一个StringSet
     *
     * @param key
     * @param values
     * @return
     */
    SPEngine putStringSet(String key, @Nullable Set<String> values);

    /**
     * 提交
     */
    void commit();


    //============================save end========================================//


    //============================get start=====================================//

    /**
     * 获取全部
     *
     * @return
     */
    Map<String, ?> getAll();

    /**
     * getString
     *
     * @param key
     * @param defValue
     * @return
     */
    @Nullable
    String getString(String key, @Nullable String defValue);

    /**
     * getBoolean
     *
     * @param key
     * @param defValue
     * @return
     */
    boolean getBoolean(String key, boolean defValue);

    /**
     * getFloat
     *
     * @param key
     * @param defValue
     * @return
     */
    float getFloat(String key, float defValue);

    /**
     * getInt
     *
     * @param key
     * @param defValue
     * @return
     */
    int getInt(String key, int defValue);

    /**
     * getLong
     *
     * @param key
     * @param defValue
     * @return
     */
    long getLong(String key, long defValue);

    /**
     * getStringSet
     *
     * @param key
     * @param defValues
     * @return
     */
    @Nullable
    Set<String> getStringSet(String key, @Nullable Set<String> defValues);

    //=============================get end======================================//


    //=============================extra start====================================//

    /**
     * contains
     *
     * @param key
     * @return
     */
    boolean contains(String key);
    //=============================extra end===================================//


    //============================clear start==================================//

    /**
     * 移除
     *
     * @param key
     * @return
     */
    SPEngine remove(String key);

    /**
     * 清除
     *
     * @return
     */
    SPEngine clear();
    //============================clear end==================================//


}
