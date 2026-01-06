package com.example.tutorial.net.nettychat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    
    // 存储所有连接的客户端通道
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    // 存储客户端ID与通道的映射关系
    private static final Map<String, Channel> clientChannels = new HashMap<>();
    
    // 存储客户端名称与通道的映射关系
    private static final Map<Channel, String> channelNames = new HashMap<>();
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client [" + incoming.remoteAddress() + "] is connecting...");
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        String clientName = channelNames.get(incoming);
        
        if (clientName != null) {
            // 移除客户端映射
            clientChannels.remove(clientName);
            channelNames.remove(incoming);
            
            // 广播客户端离开消息
            String message = "【系统消息】" + clientName + " 离开了聊天室";
            for (Channel channel : channels) {
                if (channel != incoming) {
                    channel.writeAndFlush(message + "\n");
                } else {
                    channel.writeAndFlush("您已离开聊天室\n");
                }
            }
            System.out.println(clientName + " 已断开连接");
        }
        
        channels.remove(incoming);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();
        String message = (String) msg;
        
        // 检查是否是设置用户名的消息
        if (message.startsWith("/name:")) {
            String clientName = message.substring(6).trim();
            if (!clientChannels.containsKey(clientName)) {
                // 设置客户端名称
                channelNames.put(incoming, clientName);
                clientChannels.put(clientName, incoming);
                
                // 发送确认消息给当前客户端
                incoming.writeAndFlush("用户名设置成功: " + clientName + "\n");
                
                // 广播用户加入消息
                String joinMessage = "【系统消息】" + clientName + " 加入了聊天室";
                for (Channel channel : channels) {
                    if (channel != incoming) {
                        channel.writeAndFlush(joinMessage + "\n");
                    }
                }
                System.out.println(clientName + " 加入了聊天室");
            } else {
                incoming.writeAndFlush("用户名已存在，请选择其他用户名\n");
            }
        } else {
            // 普通聊天消息
            String clientName = channelNames.get(incoming);
            if (clientName != null) {
                String broadcastMessage = clientName + ": " + message;
                System.out.println("收到消息: " + broadcastMessage);
                
                // 广播消息给所有客户端（包括发送者）
                for (Channel channel : channels) {
                    channel.writeAndFlush(broadcastMessage + "\n");
                }
            }
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channels.add(incoming);
        System.out.println("Client [" + incoming.remoteAddress() + "] is online");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client [" + incoming.remoteAddress() + "] is offline");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client [" + incoming.remoteAddress() + "] 异常");
        cause.printStackTrace();
        channels.remove(incoming);
        ctx.close();
    }
    
    // 获取在线客户端数量
    public static int getOnlineCount() {
        return channels.size();
    }
    
    // 获取在线客户端列表
    public static String getOnlineClients() {
        StringBuilder sb = new StringBuilder("在线客户端:\n");
        for (Map.Entry<Channel, String> entry : channelNames.entrySet()) {
            sb.append("- ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
