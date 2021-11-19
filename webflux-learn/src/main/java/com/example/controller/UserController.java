package com.example.controller;

import com.example.bean.User;
import com.example.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 传统的路径设置
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 以数组的形式一次返回数据
     */
    @GetMapping("/getAllUser")
    public Flux<User> getAllUser() {
        return userDao.findAll();
    }

    /**
     * 以 sse 的方式，多次返回数据
     */
    @GetMapping(value = "/getAllUserStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUserStream() {
        return userDao.findAll();
    }

    @PostMapping("insertUser")
    public Mono<User> insertUser(@RequestBody User user) {
        // spring data jpa 新增和修改都是 save，有 id 是修改，没有 id 是新增
        return userDao.save(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        // 操作数据返回 Mono, 使用 flatMap
        // 需要先查出来，根据情况进行删除会直返回
        return userDao.findById(id)
                .flatMap(x -> userDao.delete(x).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/updateUser")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return userDao.findById(user.getId())
                .flatMap(x -> {
                    x.setAge(user.getAge());
                    x.setName(user.getName());
                    return userDao.save(x);
                })
                .map(x -> new ResponseEntity<>(x, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
