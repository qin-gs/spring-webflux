package com.example.util;

import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

public class MethodInfo {

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求方式
     */
    private HttpMethod method;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 请求体
     */
    private Mono<?> body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Mono<?> getBody() {
        return body;
    }

    public void setBody(Mono<?> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", params=" + params +
                ", body=" + body +
                '}';
    }
}
