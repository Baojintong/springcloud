package com.jt.service1;

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
public class Service1Application {

    @Autowired
    private GetUUIDService getUUIDService;

    @RequestMapping("/service1")
    public String home() throws InterruptedException {
        System.out.println("service1");
        System.out.println(getUUIDService.getUUID());
        return "Hello service1";
    }

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

}
