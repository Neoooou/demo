package com.example.tut.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/22
 */

public class ThreadLocal {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        UserContext userContext = new UserContext("Jack");
        try {
            do1();
            executorService.execute(() -> do1());

        } finally {
            userContext.close();
        }

    }


    public static void do1() {
        System.out.println(UserContext.currentUser());
    }

    static class UserContext implements AutoCloseable {

        static final java.lang.ThreadLocal<String> ctx = new java.lang.ThreadLocal<>();

        public UserContext(String user) {
            ctx.set(user);
        }

        public static String currentUser() {
            return ctx.get();
        }

        @Override
        public void close() throws Exception {
            ctx.remove();
        }
    }
}
