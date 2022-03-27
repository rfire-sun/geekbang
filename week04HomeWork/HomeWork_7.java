package com.sun.geekbang.TrainingCamp.week04.homework;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HomeWork_7 {

    @Test
    public void test(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(() -> {
            Thread.sleep(1000*2);
            return 10;
        });
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());  // get会阻塞 https://bbs.huaweicloud.com/blogs/detail/258843
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
