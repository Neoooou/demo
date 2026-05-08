package com.example.tutorial.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

import javax.net.ssl.SSLException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class NettyHttpClient {

    private final Bootstrap bootstrap;
    private final EventLoopGroup group;
    private final SslContext sslContext;
    private final int connectTimeout;  // 连接超时时间（毫秒）
    private final int readTimeout;     // 读取超时时间（毫秒）

    public NettyHttpClient() {
        this(5000, 10000);  // 默认连接超时5秒，读取超时10秒
    }

    public NettyHttpClient(int connectTimeout, int readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        
        try {
            // 创建 SSL 上下文（用于 HTTPS）
            this.sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)  // 信任所有证书（生产环境应该使用正式证书）
                    .build();
        } catch (SSLException e) {
            throw new RuntimeException("Failed to create SSL context", e);
        }

        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)  // 设置连接超时
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))  // 添加读取超时处理器
                                .addLast(new HttpClientCodec())
                                .addLast(new HttpObjectAggregator(10 * 1024 * 1024))  // 增加到 10MB
                                .addLast(new NettyHttpHandler());
                    }
                });
    }

    public CompletableFuture<HttpResponse> sendRequest(String url, String method, String content) {
        return sendRequest(url, method, content, readTimeout);
    }

    public CompletableFuture<HttpResponse> sendRequest(String url, String method, String content, int timeoutMillis) {
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();

        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            String scheme = uri.getScheme();  // 获取协议：http 或 https

            // 根据协议自动确定默认端口
            int port = "https".equalsIgnoreCase(scheme) ? 443 : 80;

            ChannelFuture connectFuture = bootstrap.connect(host, port);

            // 添加连接超时监听器
            connectFuture.addListener(f -> {
                if (f.isSuccess()) {
                    Channel channel = connectFuture.channel();

                    // 如果是 HTTPS，需要添加 SSL 处理器
                    if ("https".equalsIgnoreCase(scheme)) {
                        try {
                            channel.pipeline().addFirst(sslContext.newHandler(channel.alloc(), host, port));
                        } catch (Exception e) {
                            future.completeExceptionally(new RuntimeException("Failed to add SSL handler", e));
                            channel.close();
                            return;
                        }
                    }

                    HttpRequest request = new DefaultFullHttpRequest(
                            HttpVersion.HTTP_1_1,
                            HttpMethod.valueOf(method),
                            uri.getPath(),
                            Unpooled.copiedBuffer(content, CharsetUtil.UTF_8)
                    );
                    request.headers().set(HttpHeaderNames.HOST, host);
                    request.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
                    request.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, content.length());

                    channel.writeAndFlush(request).addListener(writeFuture -> {
                        if (writeFuture.isSuccess()) {
                            // 设置回调来接收响应
                            NettyHttpHandler.setResponseFuture(future);
                            
                            // 设置总体超时
                            scheduleTimeout(future, channel, timeoutMillis);
                        } else {
                            future.completeExceptionally(writeFuture.cause());
                            channel.close();
                        }
                    });
                } else {
                    future.completeExceptionally(new java.util.concurrent.TimeoutException(
                            "Connection timeout after " + connectTimeout + "ms"));
                }
            });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        return future;
    }

    /**
     * 设置总体请求超时
     */
    private void scheduleTimeout(CompletableFuture<HttpResponse> future, Channel channel, long timeoutMillis) {
        channel.eventLoop().schedule(() -> {
            if (!future.isDone()) {
                future.completeExceptionally(new java.util.concurrent.TimeoutException(
                        "Request timeout after " + timeoutMillis + "ms"));
                channel.close();
            }
        }, timeoutMillis, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        group.shutdownGracefully();
    }

    private static final String TEST_URL = "https://developer.aliyun.com/article/1321098";

    public static void main(String[] args) {
        // 示例1：使用默认超时设置
        System.out.println("=== 示例1：默认超时设置 ===");
        NettyHttpClient client1 = new NettyHttpClient();
        HttpResponse response1 = client1.sendRequest(TEST_URL, "GET", "{}").join();
        System.out.println("状态码: " + response1.getStatusCode());
        client1.shutdown();

        // 示例2：自定义超时设置
        System.out.println("\n=== 示例2：自定义超时设置 ===");
        NettyHttpClient client2 = new NettyHttpClient(3000, 5000);  // 连接超时3秒，读取超时5秒
        HttpResponse response2 = client2.sendRequest(TEST_URL, "GET", "{}").join();
        System.out.println("状态码: " + response2.getStatusCode());
        client2.shutdown();

        // 示例3：单次请求自定义超时
        System.out.println("\n=== 示例3：单次请求自定义超时 ===");
        NettyHttpClient client3 = new NettyHttpClient();
        HttpResponse response3 = client3.sendRequest(TEST_URL, "GET", "{}", 15000).join();  // 本次请求15秒超时
        System.out.println("状态码: " + response3.getStatusCode());
        client3.shutdown();

        // 示例4：测试超时异常
        System.out.println("\n=== 示例4：测试超时异常 ===");
        NettyHttpClient client4 = new NettyHttpClient();
        try {
            // 使用一个很慢的URL来测试超时
            HttpResponse response4 = client4.sendRequest("http://httpbin.org/delay/10", "GET", "{}", 2000).join();
            System.out.println("请求成功（不应该到这里）");
        } catch (Exception e) {
            System.out.println("捕获到超时异常: " + e.getMessage());
        }
        client4.shutdown();
    }
}