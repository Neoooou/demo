package com.example.tutorial.net.sse;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * Server sent event: 持续进行数据推送
 * @Author: Neo.zr
 * @Since: 2026/3/27
 **/
public class SseServerHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.EMPTY_BUFFER
        );


        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/event-stream");
        response.headers().set(HttpHeaderNames.CACHE_CONTROL, "no-cache");
        response.headers().set(HttpHeaderNames.CONNECTION, "keep-alive");
        response.headers().set("X-Accel-Buffering", "no"); // Nginx/OpenResty 识别
        response.headers().set("X-Proxy-Buffering", "no"); // 部分云网关/CDN 识别

        ctx.writeAndFlush(response);

        ctx.writeAndFlush(new DefaultHttpContent(Unpooled.copiedBuffer("Hello World".getBytes())));

        sendEvents(ctx);
    }

    private void sendEvents(ChannelHandlerContext ctx) {
        // For demonstration, send a message every second
        new Thread(() -> {
            try {
                int count = 0;
                while (true) {
                    String data = "data: Server time: " + System.currentTimeMillis() + "\n\n";
                    ctx.writeAndFlush(new DefaultHttpContent(Unpooled.copiedBuffer(data.getBytes())));
                    Thread.sleep(1000);
                    if (count++ > 100) break; // For example, limit number of messages
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ctx.close();
            }
        }).start();
    }

}
