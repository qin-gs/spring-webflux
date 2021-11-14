package com.example.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TestService {
    public String test() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
