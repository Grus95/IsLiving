package com.example.grus95.baselibrary.bean;


import com.example.grus95.utilslibrary.json.JsonUtils;

import java.io.Serializable;


/**
 * Created by grus95 on 16/8/31
 */
public class BaseBean implements Serializable{

    public String toJson() {
        if (null != this) {
            return JsonUtils.toJson(this);
        }
        return null;
    }
}
