package com.sun.geekbang.TrainingCamp.week11.redislock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class RedisLock {

    // 得用连接池 否则有问题
    private final JedisPool jedisPool = new JedisPool("192.168.161.149", 6379);

    /**
     * redis 分布式加锁
     * @param lockStr 锁key
     */
    public boolean lock(String lockStr, int seconds){
        SetParams params = new SetParams().nx().ex(seconds);
        return "ok".equals(jedisPool.getResource().set(lockStr,lockStr,params));
//        return "OK".equals(jedisPool.getResource().set(lockStr,lockStr,"NX","EX",seconds));
    }


    public boolean unlock(String lockStr){
        String luaScript =
                "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return jedisPool.getResource().eval(luaScript, Collections.singletonList(lockStr), Collections.singletonList(lockStr)).equals(1L);
    }



}
