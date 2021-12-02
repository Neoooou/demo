package com.example.tutorial.basics;


import java.io.InputStream;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/4
 */

public class StreamDemo {
    public static void main(String[] args) {
        InputStream inputStream = new StreamDemo().getClass().getClassLoader().getResourceAsStream("");

    }
}