package com.example.tutorial.basics;

/**
 * @Author: neo.zr
 * @Created on: 2021/9/23
 */

public class StringDemo {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = s1;
        String s3 = s1.intern();
        String s4 = "abc";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.intern() == s4.intern());
    }
}
