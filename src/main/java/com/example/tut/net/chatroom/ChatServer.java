package com.example.tut.net.chatroom;

import lombok.Data;

import java.util.Set;

/**
 * @author: neo.zr
 * @since: 2021/5/6
 */

@Data
public class ChatServer {

    private int port;
    private Set<String> userNames;

    boolean hasUsers(){
        return !this.userNames.isEmpty();
    }
}
