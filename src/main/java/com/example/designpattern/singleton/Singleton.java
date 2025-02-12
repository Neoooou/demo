package com.example.designpattern.singleton;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/23
 */

public class Singleton {
    private static Singleton INSTANCE = null;

    public static Singleton getInstance(){
        if(INSTANCE == null){
            synchronized (Singleton.class){
                INSTANCE = new Singleton();
            }
        }

        return INSTANCE;
    }
}
