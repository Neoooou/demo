package com.example.tutorial.threadrelated;

import org.apache.catalina.User;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/22
 */

public class ThreadLocalDemo {


    public static void main(String[] args) throws Exception {
        UserContext userContext = new UserContext("Jack");
        try{
            do1();
            do2();
            do3();
        }finally {
            userContext.close();
        }

    }


    public static void do1(){
        System.out.println(UserContext.currentUser());
    }

    public static void do2(){
        System.out.println(UserContext.currentUser());
    }

    public static void do3(){
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
