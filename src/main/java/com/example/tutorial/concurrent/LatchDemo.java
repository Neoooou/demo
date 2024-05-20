package com.example.tutorial.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: neo.zr
 * @since: 2021/4/15
 */

public class LatchDemo {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30));

    public static void main(String[] args) {
        final int workerNum = 2;
        final CountDownLatch latch = new CountDownLatch(workerNum);

        AtomicLong atomicLong = new AtomicLong();

        for(int i=0;i < workerNum; i++){
            executor.execute(()->{
                try{
                    for(int j = 0; j < 1000; j+=1){
                        atomicLong.addAndGet(j);
                    }
                }finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(atomicLong.get());
        executor.shutdown();
    }
}
