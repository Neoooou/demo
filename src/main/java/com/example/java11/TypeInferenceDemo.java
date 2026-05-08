package com.example.java11;

import java.util.List;

/**
 * Java 类型推断原理演示
 */
public class TypeInferenceDemo {

    public static void main(String[] args) {
        // 示例1：简单的 var 类型推断
        var message = "Hello";           // 推断为 String
        var number = 42;                 // 推断为 int
        var list = List.of(1, 2, 3);     // 推断为 List<Integer>
        
        // 示例2：查看编译后的类型
        System.out.println("message 类型: " + message.getClass().getName());
        System.out.println("number 类型: " + ((Object) number).getClass().getName());
        System.out.println("list 类型: " + list.getClass().getName());
        
        // 示例3：类型推断的限制
        // var undefined;  // 编译错误：必须初始化
        // var nullVar = null;  // 编译错误：无法推断类型
        
        // 示例4：复杂的类型推断
        var complexList = List.of("A", "B", "C");
        for (var item : complexList) {
            System.out.println("Item: " + item + ", Type: " + item.getClass().getName());
        }
    }
}
