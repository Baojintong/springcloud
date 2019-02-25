package com.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey")
    public String helloService(){
        long start=System.currentTimeMillis();
        String result=restTemplate.getForEntity("http://HELLO-SERVICE/hello?name={1}",String.class,"aaaaaaa").getBody();
        long end=System.currentTimeMillis();
        System.out.println("spend time:"+(end -start));
        return result;
    }

    public String helloFallback(){
        return "error";
    }
}
