package com.example.grus95.networdlibrary.net_impl;

import android.text.TextUtils;

import com.example.grus95.networdlibrary.callback.ArrayRCB;
import com.example.grus95.networdlibrary.callback.ObjectRCB;
import com.example.grus95.networdlibrary.error.NetCode;
import com.example.grus95.networdlibrary.net_interface.IHttpEngine;
import com.example.grus95.networdlibrary.utils.json.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by grus95 on 16/8/31
 */
public class OKHttpEngine implements IHttpEngine {

    private OkHttpClient okHttpClient;
    private HashMap<Object, Object> headers;

    @Override
    public synchronized void init() {
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();
    }

    @Override
    public void setHeaders(HashMap<Object, Object> headers) {
        this.headers = headers;
    }

    @Override
    public <T> void GET(String api, HashMap<String, String> params, final ObjectRCB<T> orcb) {
        checkApi(api);
        String apiHandle = handleGetApi(api, params);
        Request request = addHeaders(new Request.Builder().url(apiHandle));
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleObjectResponse(response, orcb);
            }
        });
    }

    @Override
    public <T> void GET(String api, HashMap<String, String> params, final ArrayRCB<T> arcb) {
        checkApi(api);
        String apiHandle = handleGetApi(api, params);
        Request request = addHeaders(new Request.Builder().url(apiHandle));
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleArrayResponse(response, arcb);
            }
        });
    }


    @Override
    public <T> void POST(String api, HashMap<String, String> params, final ObjectRCB<T> orcb) {
        checkApi(api);

        Request request = getPostRequest(api, params);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleObjectResponse(response, orcb);
            }
        });


    }

    @Override
    public <T> void POST(String api, HashMap<String, String> params, final ArrayRCB<T> arcb) {
        checkApi(api);

        Request request = getPostRequest(api, params);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleArrayResponse(response, arcb);
            }
        });
    }

    private Request getPostRequest(String api, HashMap<String, String> params) {


        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }

        return addHeaders(new Request.Builder().url(api).post(builder.build()));
    }

    private void checkApi(String api) {
        if (TextUtils.isEmpty(api)) {
            throw new RuntimeException("api为无效的参数！");
        }
        init();
    }

    private String handleGetApi(String api, HashMap<String, String> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder(api);
            for (String key : params.keySet()) {
                if (sb.toString().indexOf("?") == -1) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(params.get(key).toString());
            }
            return sb.toString();
        }
        return api;
    }


    private Request addHeaders(Request.Builder builder) {
        if (this.headers != null && this.headers.size() > 0) {
            for (Object key : this.headers.keySet()) {
                builder.addHeader(String.valueOf(key), String.valueOf(this.headers.get(key)));
            }
        }
        this.headers = null;
        return builder.build();
    }

    private <T> void handleObjectResponse(Response response, ObjectRCB orcb) throws IOException {
        if (response != null && response.isSuccessful()) {
            String result = response.body().string();
            if (TextUtils.isEmpty(result) && null != orcb) {
                final Class<?> cs = getSuperClassGenricType(orcb.getClass(), 0);
                if (cs != null) {
                    try {
                        String nameCs = cs.getName();
                        T t = null;
                        if (TextUtils.equals(nameCs, JSONObject.class.getName())) {
                            t = (T) new JSONObject(result);
                        } else if (TextUtils.equals(nameCs, JSONArray.class.getName())) {
                            t = (T) new JSONArray(result);
                        } else if (TextUtils.equals(nameCs, String.class.getName())) {
                            t = (T) result;
                        } else {
                            t = (T) JsonUtils.parseBean(result, cs);
                        }
                        orcb.onSuccess(t);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            handeRequestError(response, orcb);
        }
    }

    private <T> void handleArrayResponse(Response response, ArrayRCB arcb) throws IOException {
        if (response != null && response.isSuccessful()) {
            String result = response.body().string();
            if (TextUtils.isEmpty(result) && null != arcb) {
                final Class<?> cs = getSuperClassGenricType(arcb.getClass(), 0);
                if (cs != null) {
                    try {
                        String nameCs = cs.getName();
                        ArrayList<T> tList = new ArrayList<>();
                        if (TextUtils.equals(nameCs, JSONObject.class.getName())) {
                            JSONArray json = new JSONArray(result);
                            for (int i = 0; i < json.length(); i++) {
                                tList.add((T) json.get(i));
                            }
                        } else {
                            Type type = new ParameterizedType() {
                                public Type getRawType() {
                                    return ArrayList.class;
                                }

                                public Type getOwnerType() {
                                    return null;
                                }

                                public Type[] getActualTypeArguments() {
                                    return new Type[]{cs};
                                }
                            };
                            tList = JsonUtils.getGson().fromJson(result, type);
                        }
                        arcb.onSuccess(tList);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            handeRequestError(response, arcb);
        }
    }

    private <T> void handeRequestError(Response response, ArrayRCB<T> arcb) {
        if (response != null && arcb != null) {
            arcb.onError(new NetCode(response.code()));
        }
    }

    private <T> void handeRequestError(Response response, ObjectRCB<T> orcb) {
        if (response != null && orcb != null) {
            orcb.onError(new NetCode(response.code()));
        }
    }


    /**
     * 获取父类泛型类的类型
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
        // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        // 返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }
}
