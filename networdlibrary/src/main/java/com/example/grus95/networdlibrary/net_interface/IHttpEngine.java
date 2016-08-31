package com.example.grus95.networdlibrary.net_interface;

import com.example.grus95.networdlibrary.callback.ArrayRCB;
import com.example.grus95.networdlibrary.callback.ObjectRCB;

import java.util.HashMap;


/**
 * Created by grus95 on 16/8/31
 */
public interface IHttpEngine {

    void init();

    void setHeaders(HashMap<Object, Object> headers);

    <T> void GET(String api, HashMap<String, String> params, ObjectRCB<T> orcb);

    <T> void GET(String api, HashMap<String, String> params, ArrayRCB<T> arcb);

    <T> void POST(String api, HashMap<String, String> params, ObjectRCB<T> orcb);

    <T> void POST(String api, HashMap<String, String> params, ArrayRCB<T> arcb);

}
