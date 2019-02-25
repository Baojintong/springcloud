package com.eureka.ribbon.service;

import com.eureka.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    public User getUserById(Integer id){
        return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}/{2}",User.class,"AA",id);
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    public Future<User> getUserByIdAsync(final Integer id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}/{2}",User.class,"AA",id);
            }
        };
    }

    @HystrixCommand(fallbackMethod = "fallBack",observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> getUserById1(String name,int id){
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}/{2}", User.class, name, id);
                        if(true) {
                            throw new Exception();
                        }
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @HystrixCommand(fallbackMethod = "fallBack",observableExecutionMode = ObservableExecutionMode.LAZY)
    public Observable<User> getUserById2(String name,int id){
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}/{2}", User.class, name, id);
                        if(true) {
                            throw new Exception();
                        }
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                        }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public User fallBack(String name,int id){
        User u=new User();
        u.setName("失败了");
        return u;
    }
}
