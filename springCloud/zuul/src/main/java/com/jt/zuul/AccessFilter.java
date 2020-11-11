package com.jt.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter
        /*extends ZuulFilter*/ {
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
////        RequestContext context=RequestContext.getCurrentContext();
////        HttpServletRequest request=context.getRequest();
////        System.out.println(request.getMethod()+"---"+request.getRequestURL().toString());
////        Object accessToken=request.getParameter("accessToken");
////        if(accessToken==null){
////            System.out.println("token is null");
////            context.setSendZuulResponse(false);
////            context.setResponseStatusCode(401);
////        }
//        return null;
//    }

    public static void main(String[] args) {
        String userId="1";
        String userId2="1";
        String a="a";
        System.out.println(userId==userId2);//true
        userId+=a;
        userId2+=a;
        System.out.println(userId==userId2);//false
    }
}
