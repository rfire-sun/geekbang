package com.sun.geekbang.TrainingCamp.week11.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

public class PublishOrder {

    public PublishOrder(JedisPool jedisPool, String channelName) {
        System.out.println("Start publisher order");
        try (Jedis jedis = jedisPool.getResource()) {
            int sleepTime = 0;
            for (int i = 0; i < 10; i++) {
                sleepTime = new Random().nextInt(1000) + 1;

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                jedis.publish(channelName, "order sleep " + sleepTime);
            }
            jedis.publish(channelName, "");
        }
    }
}