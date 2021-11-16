package com.example.api;

import com.example.anno.ApiServer;
import com.example.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@ApiServer("http://localhost:8080/user")
public interface UserApi {

    /**
     * 获取所有用户
     */
    @GetMapping("/")
    Flux<User> getAllUser();

    /**
     * 根据id获取用户
     */
    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable String id);
}
