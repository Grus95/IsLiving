package com.example.grus95.utilslibrary.shared_preferences.manager;


import com.example.grus95.utilslibrary.shared_preferences.sp_impl.SPAndroidEngine;
import com.example.grus95.utilslibrary.shared_preferences.sp_interface.SPEngine;

/**
 * Created by grus95 on 16/8/31
 */
public class SPManager{
    private static volatile SPEngine mSPEngine;

    /**
     * 获取唯一的SPEngine
     * @return
     */
    public static SPEngine getOnlySPEngine(){
        if (null == mSPEngine){
            synchronized (SPManager.class){
                if (null == mSPEngine){
                    mSPEngine = getSPEngine();
                }
            }
        }
        return mSPEngine;
    }


    /**
     * 获取一个SPEngine
     * @return
     */
    public static SPEngine getSPEngine(){
        return new SPAndroidEngine();
    }
}
