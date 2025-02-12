package com.example.tutorial.guava;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/3
 */

public class CacheOperation {

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                // 并发数量
                .concurrencyLevel(8)
                // 初始容量
                .initialCapacity(100)
                // 最大容量
                .maximumSize(10000)
                // 是否统计缓存情况
                .recordStats()
                // 访问后失效时间
                .expireAfterAccess(60, TimeUnit.MINUTES)
                // 写入后失效时间
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .removalListener(notification -> {
                    System.out.println("Removing " + notification.getKey() + " - " + notification.getValue());
                })
                .build(
                        new DemoCacheLoader()
                );
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(cache.get("cc") + ", time cost: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        stopwatch.reset().start();
        System.out.println(cache.get("cc") + ", time cost: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        Ticker ticker = Ticker.systemTicker();

        System.out.println(ticker.read());

        for(int i=0;i<10;i++){
            System.out.println(System.nanoTime());
        }

        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());

    }

    static class DemoCacheLoader extends CacheLoader{

        @Override
        public Object load(Object key) throws Exception {
            Thread.sleep(1000L);
            return key + ":vvv";
        }
    }
}
