package com.example.tutorial;

import com.google.common.base.Stopwatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author neo.zr
 * @since 2024/2/20
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bucket {
    /**
     * 指定的请求
     */
    private volatile String key;
    /**
     * 开始时间
     */
    private volatile Long start;
    /**
     * 定时时长
     */
    private volatile Long interval;
    /**
     * 当前次数
     */
    private volatile AtomicInteger count;
    /**
     * 请求次数
     */
    private volatile Integer limit;


    public static void main(String[] args) throws InterruptedException {
        Bucket bucket = Bucket.builder().key("entry_a").limit(5)
                .interval(2000L).build();
        Executor executor  = Executors.newFixedThreadPool(32);
        Stopwatch stopwatch = Stopwatch.createStarted();
        CountDownLatch latch = new CountDownLatch(1000);
        for(int i=0; i < 1000; ++i){
            executor.execute(()->{
                bucket.request();
                latch.countDown();
            });
        }
        latch.await(3000, TimeUnit.MILLISECONDS);

        System.out.println("elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }

    public boolean request() {
        if(start==null){
            start=new Date().getTime();
        }
        if(System.currentTimeMillis()-start<=interval){
            if(count==null){
                count=new AtomicInteger(0);
            }
            if(count.intValue()<limit) {
                count.incrementAndGet();
                System.out.println(Thread.currentThread().getName()+ " succeed to access " + key);
                return true;
            }else{
                System.out.println(Thread.currentThread().getName()+ " failed to access " + key);
                return false;
            }
        }else{
            start=new Date().getTime();
            return request();
        }
    }
}
