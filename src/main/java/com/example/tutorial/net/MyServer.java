package com.example.tutorial.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: neo.zr
 * @since: 2021/4/25
 */

public class MyServer {
    public static class MyRun implements Runnable{
        private Socket socket = null;
        public MyRun(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            BufferedReader br = null;
            PrintWriter pw = null;
            try{
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = br.readLine();
                System.out.println(line);
                pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println(new Date().toString() + " server received " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pw.close();
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Executor executor = Executors.newFixedThreadPool(10);
        try{
            serverSocket = new ServerSocket(Constant.port);
            while(true){
                Socket accept = serverSocket.accept();
                executor.execute(new MyRun(accept));
            }
        }catch(IOException ie){
            ie.printStackTrace();
        }finally {
            if(serverSocket != null){
                try{
                    serverSocket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
