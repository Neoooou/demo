package com.example.tutorial.basics;

/**
 * @author neo.zr
 * @since 2023/4/7
 */

public class OuterClass {
    String outerField = "Outer field";

    static String staticOuterField = "Static outer field";

    class InnerClass{
        void accessOutMembers(){
            System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

}
