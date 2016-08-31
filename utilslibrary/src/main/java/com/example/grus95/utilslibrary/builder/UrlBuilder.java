package com.example.grus95.utilslibrary.builder;

/**
 * Created by grus95 on 16/8/31
 */
public class UrlBuilder {
    StringBuilder url;

    public UrlBuilder(String host) {
        url = new StringBuilder(host);
    }

    public UrlBuilder add(String path) {
        url.append(path);
        return this;
    }

    public UrlBuilder add(String key, Object value) {
        url.append("&" + key + "=" + value);
        return this;
    }

    public String toString() {
        return url.toString();
    }
}
