package com.example.tut.solutions;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class Node {
    Node next;

    Integer val;

    Node(Integer val){
        this.val = val;
    }

    Node(Integer val, Node next){
        this.val = val;
        this.next = next;
    }
}
