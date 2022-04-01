package com.sun.geekbang.TrainingCamp.week04.homework;

import java.util.concurrent.atomic.AtomicInteger;

public class HomeWork_10 {
    public static void main(String[] args) {
        int count = Thread.activeCount();
        System.out.println(count);
        AtomicInteger val = new AtomicInteger(0);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
//                        increase();
                val.incrementAndGet();
            }
        });
        thread.start();

        //等待所有累加线程都结束
        while (Thread.activeCount() > count) {
            Thread.yield();
        }

        System.out.println("获取值" + val);
    }
}
