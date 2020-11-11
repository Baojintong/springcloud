package com.jt.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

//    @Bean
//	public PatternServiceRouteMapper serviceRouteMapper() throws InterruptedException {
//		PatternServiceRouteMapper mapper=new PatternServiceRouteMapper("(?<name>^.+)(?<version>.+$)","${version}/${name}");
//		return mapper;
//		//将？后面得 参数用作存放 参数后面正则匹配到的内容，形如service1可以分解为 name=service version=1
//	}

}
