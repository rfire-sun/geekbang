package com.sun.geekbang.TrainingCamp.week04.homework;

import org.junit.Test;

public class HomeWork_3 {

    private Integer num;

    @Test
    public void test1() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.num = 10;
        });
        t1.start();
        t1.join();

        System.out.println("获取值：" + num);

    }

}

