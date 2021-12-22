package com.example.tutorial.basics;

/**
 * @author: neo.zr
 * @since: 2021/3/19
 */

public class MyThread {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

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
