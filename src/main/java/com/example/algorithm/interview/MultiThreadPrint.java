package com.example.algorithm.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadPrint {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        printInTurn();
//        printInTurnWithLocks();
//        printInTurnSemaphore();
//        executorService.shutdown();
    }

    public static void printInTurn() {
        Object o = new Object();
        int times = 10;
        Runnable a = () -> {
            synchronized (o) {
                int i = 0;
                while (i++ < times) {
                    o.notify();
                    System.out.println("Thread " + Thread.currentThread().getName() + " print A");

                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        Runnable b = () -> {
            synchronized (o) {
                int i = 0;
                while (i++ < times) {
                    o.notify();
                    System.out.println("Thread " + Thread.currentThread().getName() + " print B");
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        executorService.execute(a);
        executorService.execute(b);
    }


    public static void printInTurnSemaphore() {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);

        executorService.execute(() -> print(semaphoreA, semaphoreB, 'A'));
        executorService.execute(() -> print(semaphoreB, semaphoreC, 'B'));
        executorService.execute(() -> print(semaphoreC, semaphoreA, 'C'));
    }

    public static void print(Semaphore acquire, Semaphore release, char c){
        int i = 0;
        while(i++ < 5){
            try {
                acquire.acquire();
                System.out.println(c);
                release.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void printInTurnWithLocks() {
        ReentrantLock lock = new ReentrantLock(true);
        Condition aCondition = lock.newCondition();
        Condition bCondition = lock.newCondition();
        Condition cCondition = lock.newCondition();
        AtomicInteger i = new AtomicInteger();
        int times = 15;
        Runnable a = () -> {
            while (i.getAndAdd(1) < times) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " print A");
                    bCondition.signal();
                    aCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable b = () -> {
            while (i.getAndAdd(1) < times) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " print B");
                    cCondition.signal();
                    bCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        };

        Runnable c = () -> {
            while (i.getAndAdd(1) < times) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " print C");
                    aCondition.signal();
                    cCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        };

        executorService.submit(a);
        executorService.submit(b);
        executorService.submit(c);

    }
}
