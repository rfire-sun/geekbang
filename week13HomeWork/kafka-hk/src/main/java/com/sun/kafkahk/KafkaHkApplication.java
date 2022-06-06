package com.sun.kafkahk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaHkApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(KafkaHkApplication.class, args);

    }

    @Override
    public void run(String... args) {
        DemoProducer demoProducer = applicationContext.getBean(DemoProducer.class);
        demoProducer.sendMessage("nihao  wudi  hello");
        demoProducer.sendMessageAsy("nihao  wudi  hello asn");


    }
}
