package com.sun.geekbang.TrainingCamp.week04.homework;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch进行等待
 * 使用Callable封装有返回值的接口（使用Thread/Runnable使用单独一个对象接收传值不赘述）
 * Callable必须好像必须用futureTask包一下
 * 还可以使用个线程池进行调用返回future
 */
@Data
public class HomeWork {

    private Integer num;

    @Test
    public void test1() throws InterruptedException {

        HomeWork homeWork = new HomeWork();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Worker worker = new Worker(countDownLatch, homeWork);
        worker.start();

        countDownLatch.await();

        System.out.println("获取值：" + homeWork.getNum());
    }

}


class Worker extends Thread {
    private final HomeWork homeWork;
    private final CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch, HomeWork homeWork) {
        this.countDownLatch = countDownLatch;
        this.homeWork = homeWork;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        homeWork.setNum(10);

        countDownLatch.countDown();

    }
}
