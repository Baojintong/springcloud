package com.example.feignconsumer;

import com.example.helloserviceapi.User;
import org.springframework.stereotype.Component;

@Component
public class RefactorHelloServiceFallback implements RefactorHelloService {
    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("error",0);
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
