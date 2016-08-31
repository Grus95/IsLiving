package com.example.grus95.utilslibrary.json;

import android.text.TextUtils;

import com.example.grus95.utilslibrary.log.DLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by grus95 on 16/8/31
 */
public class JsonUtils {
    private static Gson gson;

    public static class IntegerAdapter implements JsonDeserializer<Integer> {

        @Override
        public Integer deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
                throws JsonParseException {
            if (TextUtils.equals(arg0.getAsString(), "false")) {
                return 0;
            } else if (TextUtils.equals(arg0.getAsString(), "true")) {
                return 1;
            }
            return arg0.getAsInt();
        }
    }

    public static synchronized Gson getGson() {
        if (null == gson) {
            gson = new GsonBuilder().registerTypeAdapter(Integer.TYPE, new IntegerAdapter()).create();
        }
        return gson;
    }

    /**
     * 转换成JSON
     *
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        return getGson().toJson(o);
    }

    /**
     * 转换成Bean
     *
     * @param json
     * @param t
     * @return
     */
    public static <T> T parseBean(String json, Class<T> t) {
        try {
            return getGson().fromJson(json, t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换成Bean
     *
     * @param ins
     * @param t
     * @return
     */
    public static <T> T parseBeanFromStream(InputStream ins, Class<T> t) {
        try {
            return getGson().fromJson(new InputStreamReader(ins), t);
        } catch (Exception e) {
            e.printStackTrace();
            DLog.get().w("JSON Parse ERROR,parseBeanFromStream(), Class:[%0]", t);
            return null;
        }
    }

    /**
     * 转换成集合
     *
     * @param json
     * @param t
     * @return
     */
    public static <T> List<T> parseList(String json, final Class<T> t) {
        Type type = new ParameterizedType() {
            public Type getRawType() {
                return ArrayList.class;
            }

            public Type getOwnerType() {
                return null;
            }

            public Type[] getActualTypeArguments() {
                return new Type[]{t};
            }
        };
        try {
            return getGson().fromJson(json, type);
        } catch (Exception e) {
            DLog.get().w("JSON Parse ERROR,Method: parseList(), JSON:[%0],Class:[%1]", json, t);
            return null;
        }
    }

    /**
     * 转换成集合
     *
     * @param ins
     * @param t
     * @return
     */
    public static <T> List<T> parseListFromStream(InputStream ins, final Class<T> t) {
        Type type = new ParameterizedType() {
            public Type getRawType() {
                return ArrayList.class;
            }

            public Type getOwnerType() {
                return null;
            }

            public Type[] getActualTypeArguments() {
                return new Type[]{t};
            }
        };
        try {
            return getGson().fromJson(new InputStreamReader(ins), type);
        } catch (Exception e) {
            DLog.get().w("JSON Parse ERROR,Method: parseListFromStream(), Class:[%0]", t);
            return null;
        }
    }

    /**
     * 解析某个字段
     *
     * @param obj
     * @param key
     * @return
     */
    public static int parseIntJsonBykey(JSONObject obj, String key) {
        if (null != obj && obj.has(key)) {
            try {
                return obj.getInt(key);
            } catch (JSONException e) {
                DLog.get().w("JSON Parse ERROR,Method: parseJsonBykey(obj,str), JSON:[%0],key:[%1]", key);
                return 0;
            }
        }
        return 0;
    }

    /**
     * 解析某个字段
     *
     * @param obj
     * @param key
     * @return
     */
    public static String parseJsonBykey(JSONObject obj, String key) {
        if (null != obj && obj.has(key)) {
            try {
                return obj.getString(key);
            } catch (JSONException e) {
                DLog.get().w("JSON Parse ERROR,Method: parseJsonBykey(obj,str), JSON:[%0],key:[%1]", key);
                return "";
            }
        }
        return "";
    }

    /**
     * 从String中抽取key的值
     *
     * @param json
     * @param key
     * @return
     */
    public static String parseJsonBykey(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return parseJsonBykey(object, key);
        } catch (Exception e) {
            DLog.get().w("JSON Parse ERROR,Method: parseList(), JSON:[%0],Key:[%1]", json, key);
            return "";
        }
    }

    public static JSONObject hashMapToJson(HashMap hashMap) {
        JSONObject ret = new JSONObject();
        if (hashMap != null) {
            for (Object key : hashMap.keySet()) {
                try {
                    ret.put((String) key, hashMap.get(key));
                } catch (JSONException e) {
                }
            }
        }
        return ret;
    }


}
