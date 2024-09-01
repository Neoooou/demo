package com.example.tutorial.concurrent;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author neo.zr
 * @since 2024/1/31
 */

public class ThreadDemo {
    static Object o = new Object();

    // fixed thread pool
    static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws Exception {
        Thread.sleep(100); // 释放CPU资源，不释放锁资源，进入Blocked状态

        Thread.yield(); // 让出CPU时间片，线程回到runnable状态

        executorService.submit(new DemoRunnable());
        executorService.submit(new DemoRunnable1());

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
