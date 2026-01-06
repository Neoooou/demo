package com.example.tutorial.concurrent;

import java.util.concurrent.*;

/**
 * @author : neo
 **/
public class CompletionServiceD {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(10);

        CompletionService completionService = new ExecutorCompletionService(executor);

        completionService.submit(() -> {
            Thread.sleep(1000);
            return "A";
        });

        completionService.submit(() -> {
            Thread.sleep(2000);
            return "B";
        });

        completionService.submit(() -> {
            Thread.sleep(3000);
            return "C";
        });

        /**
         * 分离任务的执行和完成，最先完成的任务优先加入阻塞队列，通过toke()取出完成结果，避免多任务阻塞等待
         */
        for(int i = 0; i < 3; ++i){
            System.out.println(completionService.take().get());
        }
    }
}
