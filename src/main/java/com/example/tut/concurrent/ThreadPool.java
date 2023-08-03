package com.example.tut.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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



}
