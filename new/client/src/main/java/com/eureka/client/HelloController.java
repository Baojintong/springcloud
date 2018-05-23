package com.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId()+",param:"+name);
        return "hello world";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println("/user,host:" + instance.getHost() + ",service_id:" + instance.getServiceId()+",param:"+name);
        ///user,host:localhost,service_id:hello-service,param:aaaaaaa
        return "user world";
    }
}
