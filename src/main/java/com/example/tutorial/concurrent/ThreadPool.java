package com.example.tutorial.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author neo.zr
 * @since 2023/8/3
 */

public class ThreadPool {
    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    private static long KEEP_ALIVE_TIME = 600L;

    private static int capacity = 4028;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExecutorUtil-%d").build();

    private static final ThreadPoolExecutor COMMON_THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE
            , KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(capacity), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    // 固定线程数
    ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4027),
                new ThreadFactoryBuilder().setNameFormat("format_%d").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.execute(new RunnableTask());

        Future<Object> future = executor.submit(new CallableTask());
        System.out.println(future.get());

        Thread.activeCount();
        executor.shutdown();
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

}
