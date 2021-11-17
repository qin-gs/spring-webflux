package com.example.util;

public interface ProxyCreator {

    /**
     * 创建代理对象
     */
    Object getObject(Class<?> clazz);

}
