package com.example.tutorial.net.chatroom;

import java.io.*;
import java.net.Socket;

/**
 * @author: neo.zr
 * @since: 2021/5/6
 */

public class UserThread extends Thread{
    private Socket socket;
    private ChatServer chatServer;
    private PrintWriter writer;

    @Override
    public void run() {
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        }catch (IOException ioe){

        }
    }

    void printUsers(){
        if(chatServer.hasUsers()){
            writer.println("Connected Users: " + chatServer.getUserNames());
        }else{
            writer.println("No other Users connected");
        }
    }

    void sendMessage(String msg){
        writer.println(msg);
    }
}
