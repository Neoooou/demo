package com.example.tut.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: neo.zr
 * @since: 2021/4/25
 */

public class MyClient {
    public static void main(String[] args) {
        PrintWriter pw = null;
        BufferedReader br = null, br1 = null;
        String msg = null;
        final String exit = "exit";
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), Constant.port);
            client.setKeepAlive(true);
            pw = new PrintWriter(client.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));

            br1 = new BufferedReader(new InputStreamReader(client.getInputStream()));
            client.getInputStream();
            while(!exit.equalsIgnoreCase(msg)){
                msg = br.readLine();
                pw.println(msg);
                pw.flush();
                System.out.println(br1.readLine());
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
