package com.example.tutorial.net.nettychat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {
    
    private String host;
    private int port;
    
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline()
                       .addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                       .addLast(new StringDecoder())
                       .addLast(new StringEncoder())
                       .addLast(new ChatClientHandler());
                 }
             });
            
            ChannelFuture f = b.connect(host, port).sync();
            Channel channel = f.channel();
            
            System.out.println("连接到聊天服务器，输入 /name:你的昵称 来设置用户名");
            System.out.println("输入消息并按回车发送，输入 'quit' 退出");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = in.readLine();
                if ("quit".equalsIgnoreCase(line)) {
                    break;
                }
                channel.writeAndFlush(line + "\n");
            }
            
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        
        new ChatClient(host, port).start();
    }
}
