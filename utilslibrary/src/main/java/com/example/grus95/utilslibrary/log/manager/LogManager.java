package com.example.grus95.utilslibrary.log.manager;


import com.example.grus95.utilslibrary.log.log_impl.LoggerEngine;
import com.example.grus95.utilslibrary.log.log_interface.LogEngine;

/**
 * Created by grus95 on 16/8/31
 */
public class LogManager {

    private volatile static LogEngine mLogEngine;

    public static LogEngine getLogEngine() {
        if (null == mLogEngine) {
            synchronized (LogManager.class) {
                if (null == mLogEngine) {
                    mLogEngine = new LoggerEngine();
                }
            }
        }

        return mLogEngine;
    }
}
