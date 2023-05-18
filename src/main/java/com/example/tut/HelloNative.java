package com.example.tut;

/**
 * @Author: neo.zr
 * @Created on: 2021/7/9
 */

public class HelloNative {

    static String libName = "";

    static{
        System.loadLibrary(libName);
    }

    public static native void sayHello();

    public static void main(String[] args) {
        sayHello();
    }

}
