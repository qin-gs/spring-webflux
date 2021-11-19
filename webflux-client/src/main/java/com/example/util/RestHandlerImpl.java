package com.example.util;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class RestHandlerImpl implements RestHandler {
    private WebClient client;

    /**
     * 初始化 WebClient
     */
    @Override
    public void init(ServerInfo info) {
        this.client = WebClient.create(info.getUrl());
    }

    /**
     * 处理请求
     */
    @Override
    public Object invokeRest(MethodInfo info) {
        WebClient.ResponseSpec retrieve = this.client
                .method(info.getMethod())
                .uri(info.getUrl(), info.getParams())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
        if (info.isReturnFlux()) {
            return retrieve.bodyToFlux(info.getReturnType());
        } else {
            return retrieve.bodyToMono(info.getReturnType());
        }
    }
}
