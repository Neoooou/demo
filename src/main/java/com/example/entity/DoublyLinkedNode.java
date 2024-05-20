package com.example.entity;

/**
 * @author neo.zr
 * @since 2023/8/28
 */

public class DoublyLinkedNode extends Node {
    DoublyLinkedNode prev;
    DoublyLinkedNode next;


    protected DoublyLinkedNode(int val, DoublyLinkedNode prev, DoublyLinkedNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
