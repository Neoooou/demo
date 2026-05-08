import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClientBenchmark {
    
    private static final int CONCURRENT_USERS = 50;  // 模拟并发用户数
    private static final int TOTAL_REQUESTS = 500;   // 总请求数
    private static final String TEST_URL = "http://api.example.com/test";
    
    public static void main(String[] args) throws Exception {
        System.out.println("开始压测 Apache HttpClient vs Netty HttpClient");
        
        // 测试 Apache HttpClient
        benchmarkApacheHttpClient();
        
        // 测试 Netty HttpClient
        benchmarkNettyHttpClient();
        
        System.out.println("压测完成");
    }
    
    /**
     * 压测 Apache HttpClient
     */
    public static void benchmarkApacheHttpClient() throws Exception {
        ApacheHttpClient apacheClient = new ApacheHttpClient();
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_USERS);
        
        long startTime = System.currentTimeMillis();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);
        
        CompletableFuture<?>[] futures = new CompletableFuture[TOTAL_REQUESTS];
        
        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            final int requestId = i;
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    String response = apacheClient.sendRequest(TEST_URL, "{}").join();
                    successCount.incrementAndGet();
                    System.out.println("Apache HTTP Client - Request " + requestId + " completed: " + response);
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
        apacheClient.close();
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
                    String response = nettyClient.sendRequest(TEST_URL, "POST", "{}").join();
                    successCount.incrementAndGet();
                    System.out.println("Netty HTTP Client - Request " + requestId + " completed: " + response);
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
    
    /**
     * 测试订单处理场景
     */
    public static void benchmarkOrderProcessing() throws Exception {
        System.out.println("\n=== 订单处理场景压测 ===");
        
        // 测试 Netty 版本
        OrderServiceWithNetty nettyOrderService = new OrderServiceWithNetty();
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_USERS);
        
        long startTime = System.currentTimeMillis();
        AtomicInteger nettySuccess = new AtomicInteger(0);
        AtomicInteger nettyErrors = new AtomicInteger(0);
        
        CompletableFuture<?>[] nettyFutures = new CompletableFuture[100];  // 100个订单
        
        for (int i = 0; i < 100; i++) {
            final int orderId = i;
            nettyFutures[i] = CompletableFuture.runAsync(() -> {
                try {
                    String orderData = "{\"orderId\": \"" + orderId + "\", \"items\": [{\"id\": 1, \"quantity\": 2}]}";
                    String response = nettyOrderService.processOrder(orderData).join();
                    nettySuccess.incrementAndGet();
                    System.out.println("Netty Order " + orderId + " processed: " + response);
                } catch (Exception e) {
                    nettyErrors.incrementAndGet();
                    System.err.println("Netty Order " + orderId + " failed: " + e.getMessage());
                }
            }, executor);
        }
        
        CompletableFuture.allOf(nettyFutures).join();
        long nettyDuration = System.currentTimeMillis() - startTime;
        
        System.out.println("Netty 订单处理 - 总耗时: " + nettyDuration + "ms, 成功: " + nettySuccess.get() + ", 失败: " + nettyErrors.get());
        
        nettyOrderService.shutdown();
        executor.shutdown();
    }
}
