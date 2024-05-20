package com.example.tutorial.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author neo.zr
 * @since 2023/8/10
 */

public class CompletionStageDemo {
    public static void main(String[] args) {
        CompletionStage<Integer> stage = CompletableFuture.completedFuture(2);
        stage
                // 同步执行， 接收上一步结果作为参数，执行完成，有返回值
                .thenApply(x ->
                {
                    System.out.println("thenApply");
                    return x + 1;
                })

                //同步执行
                .thenAccept(x -> {
                    System.out.println("thenAccept");
                    x += 1;
                })
                .thenCompose(x -> {
                    System.out.println("x ="+x);
                    System.out.println("thenCompose");
                    return new CompletableFuture<>();
                })

                .toCompletableFuture()
                .obtrudeException(new Throwable());

        stage.handle((result, throwable) -> {
                    System.out.println(result);
                    System.out.println(throwable);
                    return "result";
                });
    }
}
