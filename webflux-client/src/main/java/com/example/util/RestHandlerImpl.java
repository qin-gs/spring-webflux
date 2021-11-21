package com.example.util;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
        WebClient.RequestBodySpec request = this.client
                .method(info.getMethod())
                .uri(info.getUrl(), info.getParams())
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec retrieve;
        if (Objects.nonNull(info.getBody())) {
            retrieve = request.body(info.getBody(), info.getBodyType()).retrieve();
        } else {
            retrieve = request.retrieve();
        }

        retrieve.onStatus(httpStatus -> httpStatus.value() == 404,
                clientResponse -> Mono.just(new RuntimeException("Not found")));

        Object result;
        if (info.isReturnFlux()) {
            result = retrieve.bodyToFlux(info.getReturnType());
        } else {
            result = retrieve.bodyToMono(info.getReturnType());
        }
        return result;
    }
}
