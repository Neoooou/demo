package com.example.tutorial.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author neo.zr
 * @since 2024/1/25
 */

public class Solution1 {
    static Semaphore sa = new Semaphore(1);
    static Semaphore sb = new Semaphore(0);
    static Semaphore sc = new Semaphore(0);
    static AtomicInteger c = new AtomicInteger(1);

    public static void main(String[] args) {
        printInTurn();
    }
    public static void printInTurn() {
        new Thread(() -> {
            do {
                try {
                    sa.acquire();
                    System.out.println("A");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sb.release(1);
                }
            } while (c.getAndAdd(1) < 100);

        }).start();

        new Thread(() -> {
            do {
                try {
                    sb.acquire();
                    System.out.println("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sc.release(1);
                }
            } while (c.getAndAdd(1) < 100);
        }).start();

        new Thread(() -> {
            do {
                try {
                    sc.acquire();
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sa.release(1);
                }
            } while (c.getAndAdd(1) < 100);
        }).start();
    }
}
