package com.example.exception;

import java.util.stream.Stream;

/**
 * 自定义异常校验
 */
public class SelfException extends RuntimeException {

    private String fieldName;
    private String fieldValue;

    public SelfException() {
    }

    public SelfException(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public SelfException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    private static final String[] INVALID_NAMES = {"admin", "teacher"};

    public static void checkSelfException(String s) {
        Stream.of(INVALID_NAMES)
                .filter(x -> x.equalsIgnoreCase(s))
                .findAny()
                .ifPresent(x -> {
                    throw new SelfException("name", s);
                });
    }
}
