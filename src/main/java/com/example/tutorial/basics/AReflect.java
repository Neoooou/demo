package com.example.tutorial.basics;

import java.lang.reflect.Field;
import java.time.DayOfWeek;

public class AReflect {


    public static void checkSynthetic(){
        Field[] fields = DayOfWeek.class.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            // print name of Fields
            System.out.println("The Field " + fields[i].toString() + "\n is isSynthetic:" + fields[i].isSynthetic());
        }
    }
}
