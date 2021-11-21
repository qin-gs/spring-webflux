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

    /**
     * 返回 Flux 还是 Mono
     */
    private boolean returnFlux;

    /**
     * 返回对象类型
     */
    private Class<?> returnType;

    /**
     * requestBody 类型
     */
    private Class<?> bodyType;

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

    public boolean isReturnFlux() {
        return returnFlux;
    }

    public void setReturnFlux(boolean returnFlux) {
        this.returnFlux = returnFlux;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public Class<?> getBodyType() {
        return bodyType;
    }

    public void setBodyType(Class<?> bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", params=" + params +
                ", body=" + body +
                ", returnFlux=" + returnFlux +
                ", returnType=" + returnType +
                ", bodyType=" + bodyType +
                '}';
    }
}
