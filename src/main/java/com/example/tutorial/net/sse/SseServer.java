package com.example.tutorial.net.sse;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author: Neo.zr
 * @Since: 2026/3/27
 **/
public class SseServer {
    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            // 1. HTTP 请求/响应编解码
                            p.addLast(new HttpServerCodec());
                            // 2. 聚合 HTTP 消息（SSE 通常无需读取请求体，可省略或设较小值）
                            p.addLast(new HttpObjectAggregator(8192));
                            // 3. 分块写入支持（防止大流写入导致 OOM，优化内存管理）
                            p.addLast(new ChunkedWriteHandler());
                            // 4. 你的 SSE 业务处理器
                            p.addLast(new SseServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);

            ChannelFuture f = b.bind(8080).sync();
            System.out.println("✅ SSE Server started on port 8080");
            f.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
