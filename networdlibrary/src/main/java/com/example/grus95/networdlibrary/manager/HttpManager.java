package com.example.grus95.networdlibrary.manager;


import com.example.grus95.networdlibrary.net_impl.OKHttpEngine;
import com.example.grus95.networdlibrary.net_interface.IHttpEngine;

/**
 * Created by grus95 on 16/8/31
 */
public class HttpManager {

    private volatile static HttpManager httpManager;

    private IHttpEngine iHttpEngine;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (null == httpManager) {
            synchronized (HttpManager.class) {
                if (null == httpManager) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }


    public IHttpEngine getHttpEngine() {
        if (iHttpEngine == null) {
            iHttpEngine = new OKHttpEngine();
        }
        return iHttpEngine;
    }
}
