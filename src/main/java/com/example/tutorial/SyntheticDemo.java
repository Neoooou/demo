package com.example.tutorial;

import java.lang.reflect.Field;
import java.time.DayOfWeek;

public class SyntheticDemo {

    public static void main(String[] args) {
        Field[] fields = DayOfWeek.class.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            // print name of Fields
            System.out.println("The Field " + fields[i].toString() + "\n is isSynthetic:" + fields[i].isSynthetic());
        }
    }
}
