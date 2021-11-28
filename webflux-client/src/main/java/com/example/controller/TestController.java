package com.example.controller;

import com.example.api.UserApi;
import com.example.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Locale;
import java.util.UUID;

@RestController
public class TestController {

    private final UserApi userApi;

    public TestController(UserApi userApi) {
        this.userApi = userApi;
    }

    @RequestMapping("/")
    public void test() {

        // userApi.getAllUser().subscribe(System.out::println);

        String changeId = "2";
        String phone = String.valueOf(Instant.now().getEpochSecond());
        User changeUser = User.UserBuilder.anUser().id(changeId).phoneNum(phone).build();
        userApi.updateUserById(Mono.just(changeUser))
                .subscribe(System.out::println, e -> {
                    System.err.println(e.getMessage());
                });


        // String id = getUuid();
        // userApi.addUser(Mono.just(User.UserBuilder.anUser().id(id).name("qqq").age(12).gender("M").build()))
        //         .subscribe(System.out::println, e -> {
        //             System.err.println(e.getMessage());
        //         });
        // userApi.deleteUserById(id).subscribe();

    }

    private String getUuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT);
    }
}
