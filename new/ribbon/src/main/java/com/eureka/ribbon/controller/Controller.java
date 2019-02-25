package com.eureka.ribbon.controller;

import com.eureka.ribbon.UserCommand;
import com.eureka.ribbon.UserObservableCommand;
import com.eureka.ribbon.service.UserService;
import com.eureka.ribbon.model.User;
import com.eureka.ribbon.service.HelloService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @Autowired
    UserService userServicel;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        ResponseEntity<String> responseEntity=restTemplate.postForEntity("http://HELLO-SERVICE/hello?name={1}","",String.class,"aaaaaaa");
        String body=responseEntity.getBody();
        return body;
    }
    @RequestMapping(value = "/ribbon-consumer2", method = RequestMethod.GET)
    public String helloConsumer2(){
        return helloService.helloService();
    }

    @RequestMapping(value = "/ribbon-consumer3", method = RequestMethod.GET)
    public String helloConsumer3(){
        User u=new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,17,"张三").execute();
        System.out.println(u);
        return "test";
    }

    @RequestMapping(value = "/ribbon-consumer4", method = RequestMethod.GET)
    public String helloConsumer4() throws Exception {
        Future<User> futureUser=new UserCommand (com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,26,"李四").queue();
        System.out.println("有没有执行！"+futureUser);
        if(futureUser!=null){
            System.out.println(futureUser.get());
        }
        return "test";
    }

    @RequestMapping(value = "/ribbon-consumer5", method = RequestMethod.GET)
    public String helloConsumer5(){
        User u=userServicel.getUserById(22);
        System.out.println(u);
        return "test";
    }

    @RequestMapping(value = "/ribbon-consumer6", method = RequestMethod.GET)
    public String helloConsumer6(){
        //被观察主题
        Observable<User> ho= new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,66,"刘老六").observe();

        //订阅
        ho.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                //User{name='刘老六', age=66}
                System.out.println(user);
            }
        });

        //被观察主题
        Observable<User> co= new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,6666,"李景民").toObservable();

        //创建观察者
        Observer<User> observer=new Observer<User>() {
            @Override
            public void onCompleted() {
                //onCompleted
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(User user) {//相当于普通观察者模式中观察者的update()
                //User{name='李景民', age=6666}
                System.out.println(user);
            }
        };
        //订阅
        co.subscribe(observer);
        return "test";
    }

    @RequestMapping(value = "/ribbon-consumer7", method = RequestMethod.GET)
    public String helloConsumer7() throws Exception {
        //被观察主题
        Observable<User> ho= new UserObservableCommand(HystrixObservableCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,14,"tom").observe();
        //订阅
        ho.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                //User{name='失败了', age=null}
                System.out.println(user);
            }
        });

        //被观察主题
        Observable<User> co= new UserObservableCommand(HystrixObservableCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,12,"marry").toObservable();

        //创建观察者
        Observer<User> observer=new Observer<User>() {
            @Override
            public void onCompleted() {
                //onCompleted
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(User user) {//相当于普通观察者模式中观察者的update()
                //User{name='失败了', age=null}
                System.out.println(user);
            }
        };
        //订阅
        co.subscribe(observer);
        return "test";
    }

    @RequestMapping(value = "/ribbon-consumer8", method = RequestMethod.GET)
    public String helloConsumer8() throws Exception {
        Observable<User> ho=userServicel.getUserById1("lacy",22);
        ho.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                //User{name='lacy', age=22}
                System.out.println(user);
            }
        });
        Observable<User> co=userServicel.getUserById2("jack",24);
        co.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                //User{name='jack', age=24}
                System.out.println(user);
            }
        });
        return "test";
    }
}
