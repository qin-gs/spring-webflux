package com.example.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 异常切面处理
 * <p>
 * 给需要校验的参数对象加上 @Valid
 */
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 校验错误的切面处理
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(WebExchangeBindException e) {
        // 可能有多个字段错误，拼接起来
        String errorInfo = e.getFieldErrors().stream().map(x -> x.getField() + ":" + x.getDefaultMessage())
                .reduce("", (x, y) -> x + "\n" + y);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
