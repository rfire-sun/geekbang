package com.sun.geekbang.TrainingCamp.week04.homework;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class HomeWork_8 {

    @Test
    public void test(){
        // 这里看起来好像是串行的，不知道在那里进行通信并阻塞的
        int result1 = CompletableFuture.supplyAsync(()->{return 10;}).join();

        System.out.println(10);
    }
}
