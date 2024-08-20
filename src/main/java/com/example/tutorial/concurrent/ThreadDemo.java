package com.example.tutorial.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author neo.zr
 * @since 2024/1/31
 */

public class ThreadDemo {
    static Object o = new Object();
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100); // 释放CPU资源，不释放锁资源，进入Blocked状态

        o.wait(); // 让出CPU时间片，释放当前锁

        Thread.yield(); // 让出CPU时间片，线程回到runnable状态

        executorService.submit(new DemoRunnable());
        executorService.submit(new DemoRunnable1());
    }

    static class DemoRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (o){
                try {
                    o.wait();
                    System.out.println("notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class DemoRunnable1 implements Runnable{

        @Override
        public void run() {
            synchronized (o){
                try {
//                    Thread.sleep(1000);
                    Thread.yield();
                    System.out.println("DemoRunnable1");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                o.notify();
            }
        }
    }
}
