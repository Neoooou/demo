package com.example.tutorial.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class NettyHttpHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    
    private static CompletableFuture<HttpResponse> responseFuture;
    
    public static void setResponseFuture(CompletableFuture<HttpResponse> future) {
        responseFuture = future;
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse response) {
        try {
            // 获取状态码和状态文本
            int statusCode = response.status().code();
            String statusText = response.status().reasonPhrase();
            
            // 获取所有 headers
            HttpHeaders nettyHeaders = response.headers();
            Map<String, String> headers = new HashMap<>();
            for (Map.Entry<String, String> entry : nettyHeaders) {
                headers.put(entry.getKey(), entry.getValue());
            }
            
            // 获取响应内容
            String content = response.content().toString(CharsetUtil.UTF_8);
            
            // 创建完整的响应对象
            HttpResponse httpResponse = new HttpResponse(statusCode, statusText, headers, content);
            
            if (responseFuture != null) {
                responseFuture.complete(httpResponse);
                responseFuture = null;
            }
        } finally {
            ctx.close();
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (responseFuture != null) {
            responseFuture.completeExceptionally(cause);
            responseFuture = null;
        }
        ctx.close();
    }
}