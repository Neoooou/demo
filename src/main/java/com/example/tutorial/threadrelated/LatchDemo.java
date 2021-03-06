package com.example.tutorial.threadrelated;

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
    public static void main(String[] args) {
        final int workerNum = 2;
        final CountDownLatch latch = new CountDownLatch(workerNum);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30));
        AtomicLong s = new AtomicLong();
        for(int i=0;i < workerNum; i++){
            executor.execute(()->{
                try{
                    for(int j = 0; j < 1000; j+=2){
                        s.addAndGet(j);
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
        System.out.print(s.get());
        executor.shutdown();
    }
}
