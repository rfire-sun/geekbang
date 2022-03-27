package com.sun.geekbang.TrainingCamp.week04.homework;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class HomeWork_5 {

    private Integer num;

    @Test
    public void test1() throws InterruptedException {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.num = 10;
            condition.signal(); // 得用signal
            lock.unlock();

        });
        t1.start();

        lock.lock();
        condition.await();
        lock.unlock();

        System.out.println("获取值：" + num);


    }

}

