package com.example.router;

import com.example.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return nest(
                path("/user"), // 类上面的 @RequestMapping("user")
                route(GET("/getAllUser"), handler::getAllUser) // 方法上的 @GetMapping("/)
                        .andRoute(POST("/addUser").and(accept(MediaType.APPLICATION_JSON)), handler::addUser)
                        .andRoute(DELETE("/deleteUser/{id}"), handler::deleteUser)
        );
    }
}
