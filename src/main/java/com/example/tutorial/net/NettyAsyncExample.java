package com.example.tutorial.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 * Netty 异步回调详解示例
 */
public class NettyAsyncExample {

    public static void main(String[] args) {
        // 示例1：理解异步回调
        demonstrateAsyncCallback();
        
        // 示例2：理解 Future 的作用
        demonstrateFutureUsage();
    }

    /**
     * 示例1：演示异步回调的基本概念
     */
    private static void demonstrateAsyncCallback() {
        System.out.println("=== 示例1：异步回调 ===");
        
        CompletableFuture<String> future = new CompletableFuture<>();
        
        // 模拟异步操作
        new Thread(() -> {
            System.out.println("1. 开始异步操作...");
            try {
                Thread.sleep(1000);  // 模拟耗时操作
                System.out.println("2. 异步操作完成");
                future.complete("操作结果");  // 完成异步操作
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        }).start();
        
        // 添加回调监听器
        future.thenAccept(result -> {
            System.out.println("3. 收到回调结果: " + result);
        });
        
        System.out.println("4. 主线程继续执行，不阻塞...");
        
        try {
            Thread.sleep(2000);  // 等待异步操作完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 示例2：演示 ChannelFuture 的使用
     */
    private static void demonstrateFutureUsage() {
        System.out.println("\n=== 示例2：ChannelFuture 使用 ===");
        
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new HttpClientCodec())
                                .addLast(new HttpObjectAggregator(1024 * 1024))
                                .addLast(new SimpleChannelInboundHandler<FullHttpResponse>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse response) {
                                        System.out.println("收到 HTTP 响应: " + response.status());
                                        ctx.close();
                                    }
                                });
                    }
                });
        
        try {
            // 连接并添加监听器
            ChannelFuture connectFuture = bootstrap.connect("www.baidu.com", 80);
            
            // 方式1：使用 Lambda 监听器（你问的写法）
            connectFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("✓ 连接成功 (Lambda 方式)");
                    Channel channel = connectFuture.channel();
                    
                    // 发送请求
                    HttpRequest request = new DefaultFullHttpRequest(
                            HttpVersion.HTTP_1_1,
                            HttpMethod.GET,
                            "/",
                            Unpooled.EMPTY_BUFFER
                    );
                    request.headers().set(HttpHeaderNames.HOST, "www.baidu.com");
                    
                    // 添加写监听器
                    channel.writeAndFlush(request).addListener(writeFuture -> {
                        if (writeFuture.isSuccess()) {
                            System.out.println("✓ 请求发送成功");
                        } else {
                            System.out.println("✗ 请求发送失败: " + writeFuture.cause());
                        }
                    });
                } else {
                    System.out.println("✗ 连接失败: " + future.cause());
                }
            });
            
            // 方式2：使用匿名内部类（传统方式）
            /*
            connectFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if (future.isSuccess()) {
                        System.out.println("连接成功 (匿名内部类方式)");
                        // ... 处理逻辑
                    }
                }
            });
            */
            
            Thread.sleep(3000);  // 等待响应
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
