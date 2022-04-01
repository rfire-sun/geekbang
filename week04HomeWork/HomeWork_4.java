package com.sun.geekbang.TrainingCamp.week04.homework;

import lombok.Data;
import org.junit.Test;

/**
 * locksupport不行。无法从子线程唤醒主线程，没有句柄呀
 */
@Data
public class HomeWork_4 {

    private Integer num;

    @Test
    public void test1() throws InterruptedException {

        HomeWork_4 lock = new HomeWork_4();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.num = 10;
                lock.notify();
            }

        });
        t1.start();

        synchronized (lock) {
            lock.wait();
        }


        System.out.println("获取值：" + num);

    }

}

