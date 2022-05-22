package com.sun.geekbang.TrainingCamp.week11.redislock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Main::lockTest);
        Thread thread2 = new Thread(Main::lockTest);

        thread1.start();
        thread2.start();

    }

    /**
     * 库存
     */
    private static int amount = 10;

    private static String lockStr = "lock";
    private static int EXPIRE = 10;

    private static RedisLock redisLock= new RedisLock();

    private static void lockTest(){
        System.out.println("lock test:: start sleep 10");

        if (!redisLock.lock(lockStr, EXPIRE)) {
            System.out.println("获取锁失败");
            return;
        }

        try {
            Thread.sleep(10000);
            amount -= 1;
            System.out.printf(Thread.currentThread().getName()+"库存减一 amount: %d\n", amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisLock.unlock(lockStr);
        System.out.println("lock test:: end");
    }
}
