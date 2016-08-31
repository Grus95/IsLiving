package com.example.grus95.utilslibrary.log;

import com.example.grus95.utilslibrary.log.log_interface.LogEngine;
import com.example.grus95.utilslibrary.log.manager.LogManager;

/**
 * Created by grus95 on 16/8/31
 */
public class DLog {

        public static LogEngine get(){
            return LogManager.getLogEngine();
        }
}
