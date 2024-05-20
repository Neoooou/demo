package com.example.entity;

/**
 * @author neo.zr
 * @since 2023/8/28
 *
 * definition of singly-linked list
 */

public class SinglyLinkedNode extends Node {
    public SinglyLinkedNode next;

    public SinglyLinkedNode(int val){
        this.val = val;
    }

    public SinglyLinkedNode(int val, SinglyLinkedNode next) {
        super();
        this.val = val;
        this.next = next;
    }
}
