package com.example.tut.basics;


import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/4
 */

public class StreamDemo {
    public static void main(String[] args) {


        try(InputStream inputStream = new StreamDemo().getClass().getClassLoader().getResourceAsStream("")){

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}