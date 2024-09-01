package com.example.tutorial.basics;

/**
 * @author neo.zr
 * @since 2023/8/17
 */

public abstract class BaseProcessor {

    public void process(){
        System.out.println("className " + this.getClass().getSimpleName());
    }
}
