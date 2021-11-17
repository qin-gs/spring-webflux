package com.example;

import com.example.api.UserApi;
import com.example.util.JdkProxyCreatorImpl;
import com.example.util.ProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebfluxClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxClientApplication.class, args);
    }

    @Bean
    public FactoryBean<UserApi> userApiFactoryBean(ProxyCreator creator) {
        return new FactoryBean<UserApi>() {
            /**
             * 返回代理对象
             */
            @Override
            public UserApi getObject() throws Exception {
                return (UserApi) creator.getObject(getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return UserApi.class;
            }
        };
    }

    @Bean
    ProxyCreator proxyCreator() {
        return new JdkProxyCreatorImpl();
    }

}
