package com.example.tutorial.net;

import java.io.*;
import java.net.Socket;

/**
 * @author: neo.zr
 * @since: 2021/4/25
 */

public class MyClient {
    public static void main(String[] args) {
        String host = "localhost";
        PrintWriter pw = null;
        BufferedReader br = null;
        String msg = null;
        final String exit = "exit";
        try {
            Socket client = new Socket(host, Constant.port);
            client.setKeepAlive(true);
            pw = new PrintWriter(client.getOutputStream());
            while(!exit.equalsIgnoreCase(msg)){
                br = new BufferedReader(new InputStreamReader(System.in));
                msg = br.readLine();
                System.out.println(msg);
                pw.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(pw != null){
                pw.close();
            }
            if(br != null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
