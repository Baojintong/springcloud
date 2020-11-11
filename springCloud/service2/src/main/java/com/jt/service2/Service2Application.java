package com.jt.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableFeignClients
public class Service2Application {

    @Autowired
    private GetUUIDService getUUIDService;

    @RequestMapping("/service2")
    public String home() {
        System.out.println("service2");
        System.out.println(getUUIDService.getUUID());
        return "Hello service2";
    }

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

}
