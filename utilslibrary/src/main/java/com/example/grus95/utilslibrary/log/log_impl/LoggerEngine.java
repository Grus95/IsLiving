package com.example.grus95.utilslibrary.log.log_impl;

import com.example.grus95.utilslibrary.log.log_interface.LogEngine;
import com.orhanobut.logger.Logger;


/**
 * Created by grus95 on 16/8/31
 */
public class LoggerEngine implements LogEngine {

    @Override
    public void init() {
        if (isDebug())
        Logger.init();
    }

    @Override
    public void init(String global_tag) {
        if (isDebug())
        Logger.init(global_tag);
    }

    @Override
    public void v(String tag, String msg) {
        if (isDebug())
            Logger.t(tag).v(msg);
    }

    @Override
    public void d(String tag, String msg) {
        if (isDebug())
            Logger.t(tag).d(msg);
    }

    @Override
    public void i(String tag, String msg) {
        if (isDebug())
            Logger.t(tag).i(msg);
    }

    @Override
    public void e(String tag, String msg) {
        if (isDebug())
            Logger.t(tag).e(msg);
    }

    @Override
    public void w(String tag, String msg) {
        if (isDebug())
            Logger.t(tag).w(msg);
    }

    @Override
    public void v(String msg) {
        if (isDebug()){
            Logger.v(msg);
        }
    }

    @Override
    public void d(String msg) {
        if (isDebug()){
            Logger.d(msg);
        }
    }

    @Override
    public void i(String msg) {
        if (isDebug()){
            Logger.i(msg);
        }
    }

    @Override
    public void e(String msg) {
        if (isDebug()){
            Logger.e(msg);
        }
    }

    @Override
    public void w(String msg) {
        if (isDebug()){
            Logger.w(msg);
        }
    }

    @Override
    public void d(String message, Object... args) {
        if (isDebug()){
            Logger.d(message,args);
        }
    }

    @Override
    public void e(String message, Object... args) {
        if (isDebug()){
            Logger.e(message,args);
        }
    }

    @Override
    public void i(String message, Object... args) {
        if (isDebug()){
            Logger.i(message,args);
        }
    }

    @Override
    public void w(String message, Object... args) {
        if (isDebug()){
            Logger.w(message,args);
        }
    }

    @Override
    public void v(String message, Object... args) {
        if (isDebug()){
            Logger.v(message,args);
        }
    }

    @Override
    public void wtf(String message, Object... args) {
        if (isDebug()){
            Logger.wtf(message,args);
        }
    }

    @Override
    public void json(String json) {
        if (isDebug()){
            Logger.json(json);
        }
    }

    @Override
    public void xml(String xml) {
        if (isDebug()){
            Logger.xml(xml);
        }
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        if (isDebug()){
            Logger.e(throwable,message,args);
        }
    }

    @Override
    public boolean isDebug() {
//        return BuildConfig.DEBUG;
            return true;
    }

}
