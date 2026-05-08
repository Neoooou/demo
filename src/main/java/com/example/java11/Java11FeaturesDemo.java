package com.example.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Java 11 新特性综合示例
 */
public class Java11FeaturesDemo {

    public static void main(String[] args) throws Exception {
        demonstrateVarKeyword();
        demonstrateStringMethods();
        demonstrateHttpClient();
        demonstrateCollectionMethods();
        demonstrateFileOperations();
    }

    /**
     * 1. 局部变量类型推断
     */
    private static void demonstrateVarKeyword() {
        System.out.println("=== 1. 局部变量类型推断 ===");
        
        var message = "Hello Java 11";
        var numbers = List.of(1, 2, 3, 4, 5);
        var sum = 0;
        
        for (var num : numbers) {
            sum += num;
        }
        
        System.out.println("Message: " + message);
        System.out.println(message.getClass().getName());
        System.out.println("Sum: " + sum);
        
        // Lambda 中使用 var
//        Function calculator = (var a, var b) -> a * b;
//        System.out.println("5 * 3 = " + calculator.apply(5, 3));
    }

    /**
     * 2. 字符串新方法
     */
    private static void demonstrateStringMethods() {
        System.out.println("\n=== 2. 字符串新方法 ===");
        
        var text = "  Java 11 Features  ";
        
        System.out.println("isBlank(): " + "   ".isBlank());
        System.out.println("strip(): '" + text.strip() + "'");
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");
        
        var multiline = "Line 1\nLine 2\nLine 3";
        System.out.println("lines():");
        multiline.lines().forEach(line -> System.out.println("  " + line));
        
        var repeated = "Java ".repeat(3);
        System.out.println("repeat(3): " + repeated);
    }

    /**
     * 3. HTTP Client API
     */
    private static void demonstrateHttpClient() {
        System.out.println("\n=== 3. HTTP Client API ===");
        
        try {
            var client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
            
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.baidu.com"))
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();
            
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            System.out.println("Body Length: " + response.body().length());
            
        } catch (Exception e) {
            System.out.println("HTTP Request failed: " + e.getMessage());
        }
    }

    /**
     * 4. 集合新方法
     */
    private static void demonstrateCollectionMethods() {
        System.out.println("\n=== 4. 集合新方法 ===");
        
        var immutableList = List.of("Apple", "Banana", "Orange");
        var immutableSet = Set.of(1, 2, 3, 4, 5);
        
        System.out.println("Immutable List: " + immutableList);
        System.out.println("Immutable Set: " + immutableSet);
        
        // 尝试修改会抛出异常
        try {
            immutableList.add("Grape");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable collection: " + e.getMessage());
        }
    }

    /**
     * 5. 文件操作简化
     */
    private static void demonstrateFileOperations() throws Exception {
        System.out.println("\n=== 5. 文件操作简化 ===");
        
        var path = Paths.get("java11-demo.txt");


        // 写入文件
        Files.writeString(path, "Hello Java 11!\nThis is a demo file.\nThird line.");
        
        // 读取文件为字符串
        var content = Files.readString(path);
        System.out.println("File content:\n" + content);
        
        // 读取文件为行列表
        var lines = Files.readAllLines(path);
        System.out.println("Number of lines: " + lines.size());
        
        // 清理
        Files.deleteIfExists(path);
        System.out.println("File deleted.");
    }
}
