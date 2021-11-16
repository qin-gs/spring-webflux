package com.example.controller;

import com.example.api.UserApi;
import com.example.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class UserController {

    private final UserApi api;

    public UserController(UserApi api) {
        this.api = api;
    }

    @GetMapping("/")
    public void test() {
        Flux<User> allUser = api.getAllUser();
        allUser.subscribe(System.out::println);
    }
}
