package com.eureka.client;

import com.example.helloserviceapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(/*String name*/) throws Exception {
//        ServiceInstance instance = client.getLocalServiceInstance();
//        //让处理线程等待几秒钟
//        int sleepTime=new Random().nextInt(3000);
//        System.out.println("sleepTime:"+sleepTime);
//        Thread.sleep(sleepTime);
        //System.out.println("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
//        System.out.println("/hello "+System.currentTimeMillis());
        int sleepTime=new Random().nextInt(3000);
        System.out.println("/hello,sleepTime:"+1700);
        Thread.sleep(990);
        return "hello world";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println("/user,host:" + instance.getHost() + ",service_id:" + instance.getServiceId()+",param:"+name);
        ///user,host:localhost,service_id:hello-service,param:aaaaaaa
        return "user world";
    }

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        System.out.println("/hello1 "+System.currentTimeMillis());
        return "HELLO "+name;
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello(@RequestHeader String name,@RequestHeader Integer age){
        System.out.println("/hello2 "+System.currentTimeMillis());
        return new User(name,age);
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        System.out.println("/hello3 "+System.currentTimeMillis());
        return "HELLO "+user.getName()+", "+user.getAge();
    }

    @RequestMapping(value = "/users/{name}/{age}", method = RequestMethod.GET)
    public User users(@PathVariable String name,@PathVariable Integer age) throws InterruptedException {
        System.out.println("/users/{name}/{age}");
        //Thread.sleep(30000);
        return new User(name,age);
    }
}
