package com.sun.geekbang.TrainingCamp.week04.homework;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 可尝试多次调用，检验一下
 * 我理解junit框架可以这么玩
 * 但是我先不研究了
 */
@Data
public class HomeWork_2 {

    private Integer num;

    @Test
    public void test2() throws BrokenBarrierException, InterruptedException {
        HomeWork homeWork = new HomeWork();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ExecutorService exec = Executors.newCachedThreadPool();
        Worker2 worker2 = new Worker2(cyclicBarrier,homeWork);
        exec.submit(worker2);

        cyclicBarrier.await();

        System.out.println("获取值："+homeWork.getNum());

        exec.shutdown();
    }
}



class Worker2 extends Thread{
    private final HomeWork homeWork;
    private final CyclicBarrier cyc;

    public Worker2(CyclicBarrier cyc,HomeWork homeWork) {
        this.cyc = cyc;
        this.homeWork = homeWork;
    }

    @Override
    public void run(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        homeWork.setNum(10);

        try {
            cyc.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}