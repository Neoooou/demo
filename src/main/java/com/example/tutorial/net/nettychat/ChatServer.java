package com.example.tutorial.net.nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServer {
    
    private int port;
    
    public ChatServer(int port) {
        this.port = port;
    }
    
    public void start() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(); // 接受进来的连接
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(); // 处理被接受的连接
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline()
                       .addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                       .addLast(new StringDecoder())
                       .addLast(new StringEncoder())
                       .addLast(new ChatServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, true);
            
            System.out.println("Chat Server 启动，监听端口: " + port);
            
            ChannelFuture f = b.bind(port).sync();
            
            // 添加控制台命令处理
            startConsoleCommandHandler();
            
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    // 处理控制台命令
    private void startConsoleCommandHandler() {
        Thread consoleThread = new Thread(() -> {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            while (true) {
                String command = scanner.nextLine();
                if ("/list".equals(command)) {
                    System.out.println(ChatServerHandler.getOnlineClients());
                } else if ("/count".equals(command)) {
                    System.out.println("在线人数: " + ChatServerHandler.getOnlineCount());
                } else if ("/exit".equals(command)) {
                    System.out.println("关闭服务器...");
                    System.exit(0);
                } else if (command.startsWith("/broadcast:")) {
                    String broadcastMsg = command.substring(11);
                    String fullMessage = "[系统广播] " + broadcastMsg;
                    for (Channel channel : ChatServerHandler.channels) {
                        channel.writeAndFlush(fullMessage + "\n");
                    }
                    System.out.println("广播消息已发送: " + broadcastMsg);
                }
            }
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }
    
    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        new ChatServer(port).start();
    }
}
