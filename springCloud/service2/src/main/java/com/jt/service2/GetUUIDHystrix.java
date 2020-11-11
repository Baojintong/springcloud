package com.jt.service2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetUUIDHystrix implements GetUUIDService {
    @Override
    public String getUUID() {
        return "get uuid failed";
    }
}
