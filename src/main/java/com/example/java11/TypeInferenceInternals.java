package com.example.java11;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 类型推断内部实现原理分析
 */
public class TypeInferenceInternals {

    public static void main(String[] args) throws Exception {
        demonstrateBytecodeEquivalence();
        demonstrateReflectionAnalysis();
        demonstrateTypeSafety();
    }

    /**
     * 1. 字节码等价性证明
     * 
     * 使用 var 和显式类型声明的变量生成的字节码完全相同
     */
    private static void demonstrateBytecodeEquivalence() {
        System.out.println("=== 1. 字节码等价性 ===");
        
        // 使用 var 的代码
        var varMessage = "Hello from var";
        
        // 使用显式类型的代码
        String explicitMessage = "Hello from explicit";
        
        // 两者在运行时完全相同
        System.out.println("var message: " + varMessage);
        System.out.println("explicit message: " + explicitMessage);
        
        // 类型检查
        System.out.println("var 是 String: " + (varMessage instanceof String));
        System.out.println("explicit 是 String: " + (explicitMessage instanceof String));
    }

    /**
     * 2. 反射分析类型信息
     * 
     * 通过反射验证类型在编译时已确定
     */
    private static void demonstrateReflectionAnalysis() throws Exception {
        System.out.println("\n=== 2. 反射类型分析 ===");
        
        // 创建测试类实例
        TypeInferenceTest test = new TypeInferenceTest();
        
        // 通过反射获取字段类型
        Field varField = test.getClass().getDeclaredField("varField");
        Field explicitField = test.getClass().getDeclaredField("explicitField");
        
        System.out.println("varField 类型: " + varField.getType().getName());
        System.out.println("explicitField 类型: " + explicitField.getType().getName());
        
        // 验证两者类型相同
        System.out.println("类型相同: " + varField.getType().equals(explicitField.getType()));
    }

    /**
     * 3. 类型安全性验证
     * 
     * var 不会牺牲类型安全性
     */
    private static void demonstrateTypeSafety() {
        System.out.println("\n=== 3. 类型安全性 ===");
        
        var text = "Hello";
        var number = 42;
        
        // 编译时类型检查
        // text = 123;  // 编译错误：类型不匹配
        // number = "world";  // 编译错误：类型不匹配
        
        // 正确的类型转换
        String upperCase = text.toUpperCase();
        int doubled = number * 2;
        
        System.out.println("Text: " + upperCase);
        System.out.println("Number: " + doubled);
    }
}

/**
 * 测试类：用于反射分析
 */
class TypeInferenceTest {
    // 使用 var 声明的字段（编译错误：var 不能用于字段）
    // private var varField = "test";
    
    // 正确的显式类型声明
    private String explicitField = "test";
    
    // 局部变量可以使用 var
    public void demonstrateLocalVar() {
        var localVar = "local variable";
        System.out.println(localVar);
    }
}
