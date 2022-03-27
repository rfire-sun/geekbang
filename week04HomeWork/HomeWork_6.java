package com.sun.geekbang.TrainingCamp.week04.homework;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HomeWork_6 {

    @Test
    public void test(){
        //第一种方式
        FutureTask<Integer> task = new FutureTask<Integer>(() -> 10);
        new Thread(task).start();

        try {
            System.out.println("result: " + task.get());  // 所有返回futureTask的貌似都可以，get时候会阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
