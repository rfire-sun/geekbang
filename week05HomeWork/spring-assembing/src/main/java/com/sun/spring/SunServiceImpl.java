package com.sun.spring;

import org.springframework.stereotype.Service;

@Service
public class SunServiceImpl implements SunService{

    @Override
    public void doService() {
        System.out.println("soService");
    }
}
