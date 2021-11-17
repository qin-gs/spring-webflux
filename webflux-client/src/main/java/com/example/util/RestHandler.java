package com.example.util;

public interface RestHandler {
    /**
     * 初始化服务器信息
     */
    void init(ServerInfo info);

    /**
     * 调用rest请求，得到结果
     */
    Object invokeRest(MethodInfo info);
}
