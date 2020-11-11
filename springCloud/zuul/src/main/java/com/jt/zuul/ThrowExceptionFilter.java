package com.jt.zuul;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

//@Component
public class ThrowExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("this is pre filter,it will throw a RuntimeException");
        doSomething();
        return null;
    }

    private void doSomething(){
        //throw new RuntimeException("Exist some errors");
    }
}
