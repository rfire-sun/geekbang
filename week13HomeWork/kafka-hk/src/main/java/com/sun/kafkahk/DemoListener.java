package com.sun.kafkahk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener {

    @KafkaListener(topics = "sun-topic", groupId = "sun-group")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group sun-group from sun-topic: " + message);
    }
}