package com.example.controller;

import com.example.api.UserApi;
import com.example.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private final UserApi userApi;

    public TestController(UserApi userApi) {
        this.userApi = userApi;
    }

    @RequestMapping("/")
    public void test() {
        userApi.getAllUser();
        userApi.getUserById("user id");
        userApi.addUser(Mono.just(User.UserBuilder.anUser().id("user id").name("a user").age(23).build()));
    }
}
