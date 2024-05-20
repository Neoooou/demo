package com.example.tutorial.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author neo.zr
 * @since 2024/2/27
 */

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture future = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "demo";
        });
        System.out.println(future.get(600, TimeUnit.MILLISECONDS));
    }
}
