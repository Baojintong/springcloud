package com.eureka.ribbon;

import com.eureka.ribbon.model.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;



public class UserCommand extends HystrixCommand<User>{

    private RestTemplate restTemplate;
    private Integer id;
    private String name;

    public UserCommand(Setter setter,RestTemplate restTemplate,Integer id,String name) {
        super(setter);
        this.id=id;
        this.restTemplate=restTemplate;
        this.name=name;
    }

    @Override
    protected User run() throws Exception {
        User user=restTemplate.getForObject("http://HELLO-SERVICE/users/{1}/{2}",User.class,name,id);
        if(true){
            throw new Exception();
        }
        return user;
    }

    @Override
    protected User getFallback() {
        User u=new User();
        u.setName("失败了");
        return u;
    }
}
