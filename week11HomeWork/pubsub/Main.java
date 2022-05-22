package com.sun.geekbang.TrainingCamp.week11.pubsub;

import redis.clients.jedis.JedisPool;

public class Main {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("192.168.161.149", 6379);
        String channelName = "ORDER";

        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool, channelName);
        PublishOrder publishOrder = new PublishOrder(jedisPool, channelName);
    }

}
