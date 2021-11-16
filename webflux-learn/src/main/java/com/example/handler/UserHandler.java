package com.example.handler;

import com.example.bean.User;
import com.example.dao.UserDao;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    private final UserDao userDao;

    public UserHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 获取所有用户
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(userDao.findAll(), User.class);
    }

    /**
     * 添加用户
     */
    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(x -> {
            // 这里进行参数校验，异常通过切面统一处理
            return ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                    .body(userDao.saveAll(userMono), User.class);
        });
        // return ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
        //         .body(userDao.saveAll(userMono), User.class);
    }

    /**
     * 根据id删除用户
     */
    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return userDao.findById(id)
                .flatMap(x -> userDao.delete(x).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }
}
