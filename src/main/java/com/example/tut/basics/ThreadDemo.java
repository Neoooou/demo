package com.example.tut.basics;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: neo.zr
 * @since: 2021/3/19
 */

public class ThreadDemo {

    /**
     * thread local variables
     */
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();


    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    private static long KEEP_ALIVE_TIME = 600L;

    private static int capacity = 4028;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExecutorUtil-%d").build();

    private static final ThreadPoolExecutor COMMON_THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(capacity),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy() //

    );

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 1 current value: " + threadLocal.get());
                    threadLocal.set(10);
                    Thread.sleep(500);
                    System.out.println("Thread 1 current value: " + threadLocal.get());
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 2 current value: " + threadLocal.get());
                    threadLocal.set(20);
                    Thread.sleep(500);
                    System.out.println("Thread 2 current value: " + threadLocal.get());
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
