package com.example.tutorial.threadrelated;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.catalina.User;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/22
 */

public class ThreadLocalDemo {
    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    private static long KEEP_ALIVE_TIME = 600L;

    private static int capacity = 4028;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExecutorUtil-%d").build();

    private static final ThreadPoolExecutor COMMON_THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE
            , KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(capacity), threadFactory, new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {
        UserContext userContext = new UserContext("Jack");
        try{
            do1();
            COMMON_THREAD_POOL.execute(()->do1());

        }finally {
            userContext.close();
        }

    }


    public static void do1(){
        System.out.println(UserContext.currentUser());
    }

    static class UserContext implements  AutoCloseable{

        static final ThreadLocal<String> ctx = new ThreadLocal<>();

        public UserContext(String user){
            ctx.set(user);
        }

        public static String currentUser(){
            return ctx.get();
        }
        @Override
        public void close() throws Exception {
            ctx.remove();
        }
    }
}
