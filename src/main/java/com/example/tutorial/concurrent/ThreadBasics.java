package com.example.tutorial.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ranz
 * @Since: 2025/1/20
 */
public class ThreadBasics {

    /**
     * volatile: 保证变量更新后，第一时间从高速缓存写入到主存
     * 不保证原子性，保证可见性
     * 有序性：指令重排, 为了提升程序的运行效率，优化代码的执行顺序，同时保证执行结果的一致
     */
    volatile int i = 0;

    // 序列化忽略
    transient int ignore = 100;

    ExecutorService pool = Executors.newFixedThreadPool(10);

    public void latchDemo(){
        final int workerNum = 2;
        final CountDownLatch latch = new CountDownLatch(workerNum);

        AtomicLong atomicLong = new AtomicLong();

        for(int i=0;i < workerNum; i++){
            pool.execute(()->{
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
        pool.shutdown();
    }

    public void reentrantLockDemo(){
        ReentrantLock lock = new ReentrantLock(true);

        List<Runnable> tasks = Lists.newArrayList();
        for(int i = 0; i < 100; i++){
            final int si = i;
            pool.submit(() -> {
                lock.lock();
                try{
                    System.out.println("i=" + si);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });

            System.out.println(i);
        }
    }
}
