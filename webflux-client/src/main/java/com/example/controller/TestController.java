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

        String id = "2";
        // userApi.getAllUser().subscribe(System.out::println);
        // userApi.deleteUserById(id).subscribe();

        userApi.addUser(Mono.just(User.UserBuilder.anUser().id("4").name("qqq").age(12).gender("M").build()))
                .subscribe(System.out::println, e -> {
                    System.err.println(e.getMessage());
                });
    }
}
