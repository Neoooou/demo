package com.example.tutorial.net;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.openqa.selenium.remote.http.HttpClient;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClientBenchmark {
    
    private static final int CONCURRENT_USERS = 50;  // 模拟并发用户数
    private static final int TOTAL_REQUESTS = 500;   // 总请求数
    private static final String TEST_URL = "https://developer.aliyun.com/article/1321098";
    
    public static void main(String[] args) throws Exception {
        System.out.println("开始压测 Apache HttpClient vs Netty HttpClient");
        
        // 测试 Apache HttpClient
//        benchmarkApacheHttpClient();
        
        // 测试 Netty HttpClient
        benchmarkNettyHttpClient();
        
        System.out.println("压测完成");

    }
    
    /**
     * 压测 Apache HttpClient
     */
    public static void benchmarkApacheHttpClient() throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)              // 连接超时时间 5秒
                .setConnectionRequestTimeout(10000)    // 连接请求超时时间 3秒
                .setSocketTimeout(30000)              // Socket超时时间 10秒
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);              // 最大连接数
        connectionManager.setDefaultMaxPerRoute(20);     // 每个路由最大连接数


        CloseableHttpClient httpClient = httpClientBuilder
                .setDefaultRequestConfig(config)
                .setConnectionManager(connectionManager)
                .build();

        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_USERS);

        long startTime = System.currentTimeMillis();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);
        
        CompletableFuture<?>[] futures = new CompletableFuture[TOTAL_REQUESTS];
        
        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            final int requestId = i;
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    HttpGet httpGet = new HttpGet(TEST_URL);
                    CloseableHttpResponse response = httpClient.execute(httpGet, HttpClientContext.create());
                    successCount.incrementAndGet();
                    System.out.println("Apache HTTP Client - Request " + requestId + " completed httpStatusCode: " +
                            response.getStatusLine().getStatusCode());
                } catch (Exception e) {
                    errorCount.incrementAndGet();
                    System.err.println("Apache HTTP Client - Request " + requestId + " failed: " + e.getMessage());
                }
            }, executor);
        }
        
        // 等待所有请求完成
        CompletableFuture.allOf(futures).join();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("\n=== Apache HttpClient 压测结果 ===");
        System.out.println("总耗时: " + duration + " ms");
        System.out.println("成功率: " + (successCount.get() * 100.0 / TOTAL_REQUESTS) + "%");
        System.out.println("成功请求数: " + successCount.get());
        System.out.println("失败请求数: " + errorCount.get());
        System.out.println("平均响应时间: " + (duration * 1.0 / TOTAL_REQUESTS) + " ms");
        System.out.println("QPS: " + (TOTAL_REQUESTS * 1000.0 / duration));
        
        executor.shutdown();

    }
    
    /**
     * 压测 Netty HttpClient
     */
    public static void benchmarkNettyHttpClient() throws Exception {
        NettyHttpClient nettyClient = new NettyHttpClient();
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_USERS);
        
        long startTime = System.currentTimeMillis();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);
        
        CompletableFuture<?>[] futures = new CompletableFuture[TOTAL_REQUESTS];
        
        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            final int requestId = i;
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    HttpResponse response = nettyClient.sendRequest(TEST_URL, "GET", "{}").join();
                    successCount.incrementAndGet();
                    System.out.println("Netty HTTP Client - Request " + requestId + " completed， http status code: " + response.getStatusCode());
                } catch (Exception e) {
                    errorCount.incrementAndGet();
                    System.err.println("Netty HTTP Client - Request " + requestId + " failed: " + e.getMessage());
                }
            }, executor);
        }
        
        // 等待所有请求完成
        CompletableFuture.allOf(futures).join();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("\n=== Netty HttpClient 压测结果 ===");
        System.out.println("总耗时: " + duration + " ms");
        System.out.println("成功率: " + (successCount.get() * 100.0 / TOTAL_REQUESTS) + "%");
        System.out.println("成功请求数: " + successCount.get());
        System.out.println("失败请求数: " + errorCount.get());
        System.out.println("平均响应时间: " + (duration * 1.0 / TOTAL_REQUESTS) + " ms");
        System.out.println("QPS: " + (TOTAL_REQUESTS * 1000.0 / duration));
        
        executor.shutdown();
        nettyClient.shutdown();
    }

}
