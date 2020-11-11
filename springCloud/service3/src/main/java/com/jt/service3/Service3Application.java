package com.jt.service3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service3Application {

    public static void main(String[] args) {
        SpringApplication.run(Service3Application.class, args);
    }

    @RequestMapping("/getUUID")
    public String getUUID() {
        String uuid = UUID.randomUUID().toString(); //转化为String对象
        uuid = uuid.replace("-", "");
        return uuid+"-------------";
    }
}
