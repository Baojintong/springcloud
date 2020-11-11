package com.jt.service1;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//service3提供了获取uuid的服务
@FeignClient(name = "service3",fallback = GetUUIDHystrix.class)
public interface GetUUIDService {
    @RequestMapping("/getUUID")
    String getUUID();
}
