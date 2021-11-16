package com.example.handler;

import com.example.exception.SelfException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 统一处理异常
 */
@Order(-2)
@Component
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 设置响应头
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);

        String message = toStr(ex);
        DataBuffer wrap = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(wrap));
    }

    private String toStr(Throwable ex) {
        if (ex instanceof SelfException e) {
            return e.getFieldName() + ": " + e.getFieldValue();
        } else {
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
