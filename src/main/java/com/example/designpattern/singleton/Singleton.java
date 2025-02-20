package com.example.designpattern.singleton;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/23
 */

public class Singleton {
    private static volatile Singleton INSTANCE = null;

    public static Singleton getInstance(){
        if(INSTANCE == null){
            synchronized (Singleton.class){
                if(INSTANCE == null){
                    INSTANCE = new Singleton();
                }
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton singletonEnum = getInstance();
        for(int i=0;i<10;++i){
            System.out.println(singletonEnum == getInstance());
        }
    }
}
