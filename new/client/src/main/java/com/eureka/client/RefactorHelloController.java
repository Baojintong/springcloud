package com.eureka.client;

import com.example.helloserviceapi.HelloService;
import com.example.helloserviceapi.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RefactorHelloController implements HelloService{

    @Override
    public String hello(@RequestParam String name){
        System.out.println("/hello4");
        int sleepTime=new Random().nextInt(3000);
        System.out.println("sleepTime:"+sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello "+name;
    }

    @Override
    public User hello(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        System.out.println("/hello5");
        return new User(name,age);
    }

    @Override
    public String hello(@RequestBody  User user) {
        System.out.println("/hello6");
        return "Hello "+user.getName()+", "+user.getAge();
    }
}
