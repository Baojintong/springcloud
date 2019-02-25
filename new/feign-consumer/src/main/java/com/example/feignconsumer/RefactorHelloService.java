package com.example.feignconsumer;

import com.example.helloserviceapi.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "hello-service",fallback = RefactorHelloServiceFallback.class)
public interface RefactorHelloService extends HelloService{
}
