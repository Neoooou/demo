package com.example.tutorial.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.ref.WeakReference;
import java.util.concurrent.*;

/**
 * 线程池：池化技术的使用，把可以复用的资源放入池子中，需要时直接复用，避免资源的频繁创建和销毁，提供资源的利用率
 * @author neo.zr
 * @since 2024/1/31
 */

public class PoolDemo {
    static Object o = new Object();

    // fixed thread pool
    static ExecutorService FIXED_POOL = Executors.newFixedThreadPool(1);

    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    private static long KEEP_ALIVE_TIME = 600L;

    private static int capacity = 4028;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExecutorUtil-%d").build();

    private static final ThreadPoolExecutor CUST_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE // 核心线程数，闲置时保留的线程数量
            ,MAX_POOL_SIZE // 最大线程数，
            ,KEEP_ALIVE_TIME
            ,TimeUnit.SECONDS
            ,new LinkedBlockingDeque<>(capacity)
            ,threadFactory
            ,new ThreadPoolExecutor.AbortPolicy());


    /**
     * 传统线程执行
     */
    public static void execThread(){
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "exec.")).start();
    }

    public static void execWithPool(){
        Runnable task = () -> System.out.println(Thread.currentThread().getName() + "exec.");
        FIXED_POOL.submit(task);
    }


    public static void main(String[] args) throws Exception {
        Thread.sleep(100); // 释放CPU资源，不释放锁资源，进入Blocked状态

        Thread.yield(); // 让出CPU时间片，线程回到runnable状态

        FIXED_POOL.submit(new DemoRunnable());
        FIXED_POOL.submit(new DemoRunnable1());

        extracted(1);

        WeakReference<String> weakReference = new WeakReference<String>(new String("Hello WeakReference"));
        System.out.println(weakReference.get());
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(weakReference.get());
    }

    static class Entity extends WeakReference<String>{
        /** The value associated with this ThreadLocal. */
        public Object value;

        Entity(String k, Object v) {
            super(k);
            value = v;
        }
    }

    private static void extracted(int i) {
        UserContext userContext = null;
        try {
            userContext = new UserContext(i+"");

            Thread.sleep(100);
            System.out.println(UserContext.currentUser());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if(userContext != null){
                userContext.close();

            }
        }
    }

    static class RunnableTask implements Runnable{

        @Override
        public void run() {

        }
    }

    static class CallableTask implements Callable{
        @Override
        public Object call() throws Exception {
            return 1;
        }
    }

    static class DemoRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (o) {
                try {
                    o.wait();
                    System.out.println("notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class DemoRunnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (o) {
                try {
                    Thread.sleep(1000);

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

    static class UserContext implements AutoCloseable {

        static final ThreadLocal<String> ctx = new ThreadLocal<>();

        public UserContext(String user) {
            ctx.set(user);
        }

        public static String currentUser() {
            return ctx.get();
        }

        @Override
        public void close() {
            ctx.remove();
        }
    }
}
