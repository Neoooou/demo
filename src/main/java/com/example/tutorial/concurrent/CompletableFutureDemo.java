package com.example.tutorial.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.stream.Collectors.toList;

/**
 * @author neo.zr
 * @since 2024/2/27
 */

public class CompletableFutureDemo {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<String> arrs = Lists.newArrayList("a", "b", "c");

        List<CompletableFuture<String>> futrueStrs = arrs.stream().map(str -> CompletableFuture.supplyAsync(()->{
            try {
                if("a".equals(str)){
                    Thread.sleep(10000);
                }else{
                    Thread.sleep(1000);
                }
                System.out.println(Thread.currentThread().getName() + "exec - " + str + " - "+  System.currentTimeMillis());
                throw new RuntimeException("null");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Success";
        })
        ).collect(toList());
        List<String> results = futrueStrs.stream().map(CompletableFuture::join).collect(toList());

        System.out.println(results);

        System.out.println("Time cost = " + stopwatch.elapsed(TimeUnit.SECONDS));
    }

}
