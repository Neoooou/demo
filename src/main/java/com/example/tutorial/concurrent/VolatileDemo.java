package com.example.tutorial.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author neo.zr
 * @since 2024/1/31
 */

public class VolatileDemo {

    /**
     * volatile: 保证变量更新后，第一时间从高速缓存写入到主存
     * 不保证原子性，保证可见性
     * 有序性：指令重排, 为了提升程序的运行效率，优化代码的执行顺序，同时保证执行结果的一致
     */
    volatile int i = 0;

    transient int ignore = 100;

    AtomicInteger atomicInteger = new AtomicInteger(0);


    ExecutorService executor = Executors.newFixedThreadPool(100);

    public void testConcurrentSet(){
        final CountDownLatch latch = new CountDownLatch(10000);
        for(int j = 0; j< 10000; ++j){
            executor.execute(() -> {
                atomicInteger.getAndAdd(1);
                latch.countDown();
            });
        }

        try {
            latch.await(1000, TimeUnit.SECONDS);
            System.out.println(atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicInteger.get());
        executor.shutdown();

    }

    public static void main(String[] args) {
        new VolatileDemo().testConcurrentSet();
    }
}
