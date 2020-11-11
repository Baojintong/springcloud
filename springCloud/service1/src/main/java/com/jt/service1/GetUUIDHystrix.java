package com.jt.service1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GetUUIDHystrix implements GetUUIDService {
    @Override
    public String getUUID() {
        return "get uuid failed";
    }

    public static void main(String[] args) {
        List<Integer> a=new ArrayList();
        a.add(2);
        a.add(1);
        a.add(3);
        Collections.sort(a);
        a.size();
    }

}
