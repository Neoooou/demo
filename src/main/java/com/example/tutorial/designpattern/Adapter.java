package com.example.tutorial.designpattern;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.Callable;

/**
 * @author: neo.zr
 * @since: 2021/4/20
 */

public class Adapter {
    public static void main(String[] args) {


    }
}


class Task implements Runnable{
    private Callable c;
    Task(Callable c){
        this.c = c;
    }
    @Override
    public void run() {
        try {
            c.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Task1 implements Callable{

    @Override
    public Object call() throws Exception {
        return null;
    }
}
