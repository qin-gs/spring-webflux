package com.example.util;

import com.example.anno.ApiServer;
import com.example.api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 创建jdk动态代理类
 */
public class JdkProxyCreatorImpl implements ProxyCreator {

    private static final Logger log = LoggerFactory.getLogger(JdkProxyCreatorImpl.class);

    @Override
    public Object getObject(Class<?> clazz) {
        // 根据接口得到api服务器信息
        ServerInfo info = extractServerInfo(clazz);
        log.info("ServerInfo: " + info);

        // 每个代理类一个
        RestHandler handler = new RestHandlerImpl();
        // 初始化服务器信息
        handler.init(info);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{UserApi.class},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 根据方法和参数得到调用信息
                        MethodInfo info = extractMethodInfo(method, args);
                        return handler.invokeRest(info);
                    }

                    /**
                     * 提取方法信息
                     */
                    private MethodInfo extractMethodInfo(Method method, Object[] args) {
                        MethodInfo info = new MethodInfo();
                        extractUrlAndMethod(method, info);
                        extractParamsAndBody(method, args, info);
                        log.info("MethodInfo: " + info);
                        return info;
                    }

                    /**
                     * 得到请求路径和请求体
                     */
                    private void extractParamsAndBody(Method method, Object[] args, MethodInfo info) {
                        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                        info.setParams(params);
                        Parameter[] parameters = method.getParameters();

                        IntStream.range(0, parameters.length).forEach(i -> {
                            Parameter parameter = parameters[i];
                            PathVariable path = parameter.getAnnotation(PathVariable.class);
                            if (Objects.nonNull(path)) {
                                params.put(path.value(), args[i]);
                            }
                            RequestBody body = parameter.getAnnotation(RequestBody.class);
                            if (Objects.nonNull(body)) {
                                info.setBody(((Mono<?>) args[i]));
                            }
                        });
                    }

                    /**
                     * 得到请求url和请求方法
                     */
                    private void extractUrlAndMethod(Method method, MethodInfo info) {
                        Annotation[] annotations = method.getAnnotations();
                        Arrays.stream(annotations)
                                .forEach(x -> {
                                    if (x instanceof GetMapping mapping) {
                                        info.setUrl(mapping.value()[0]);
                                        info.setMethod(HttpMethod.GET);
                                    } else if (x instanceof PostMapping mapping) {
                                        info.setUrl(mapping.value()[0]);
                                        info.setMethod(HttpMethod.POST);
                                    }
                                });
                    }
                });
    }

    /**
     * 其他服务器信息
     */
    private ServerInfo extractServerInfo(Class<?> clazz) {
        ServerInfo info = new ServerInfo();
        ApiServer annotation = clazz.getAnnotation(ApiServer.class);
        info.setUrl(annotation.value());
        return info;
    }
}
