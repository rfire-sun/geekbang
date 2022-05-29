package com.sun.activemqhk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms    //启动消息队列
public class ActivemqHkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqHkApplication.class, args);
    }

}
