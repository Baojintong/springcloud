package com.example.feignconsumer.controller;

import com.example.feignconsumer.RefactorHelloService;
import com.example.feignconsumer.TestService;
import com.example.helloserviceapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

//    @Autowired
//    RefactorHelloService refactorHelloService;

    @Autowired
    TestService testService;

    @Autowired
    RefactorHelloService refactorHelloService;

//    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
//    public String helloConsumer(){
//        return helloService.hello();
//    }
//
    @RequestMapping(value = "feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuilder sb=new StringBuilder();
        sb.append(testService.hello()).append("\n");
        sb.append(testService.hello("DIDI")).append("\n");
        sb.append(testService.hello("DIDI",30)).append("\n");
        sb.append(testService.hello(new User("DIDI",30))).append("\n");
        return sb.toString();
    }

//    @RequestMapping(value = "feign-consumer3",method = RequestMethod.GET)
//    public String helloConsumer3(){
//        StringBuilder sb=new StringBuilder();
//        sb.append(testService.hello("MINI")).append("\n");
//        return sb.toString();
//    }
//
    @RequestMapping(value = "feign-consumer4",method = RequestMethod.GET)
    public String helloConsumer4(){
        StringBuilder sb=new StringBuilder();
        sb.append(refactorHelloService.hello("MINI")).append("\n");
        return sb.toString();
    }


}
