package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/21
 */

public class PrintUtil {

    public static void main(String[] args) {
        B b = new B("1", "2", 3, new A("1"));
        printObj(b);
    }
    public static void printObj(Object o){
        Field[] fs = o.getClass().getDeclaredFields();
        for(Field f: fs){
            f.setAccessible(true);
            try {
                if(f.getType().isPrimitive() || f.getType().getName() == "java.lang.String")
                    System.out.println(f.getName() + " - " + f.get(o));
                else
                    printObj(f.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

@Data
@AllArgsConstructor
class B{
    String a;
    String b;
    private int c;
    A o;
}

@AllArgsConstructor
class A{
    String a;
}