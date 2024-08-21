package com.example.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neo.zr
 * @since 2023/8/27
 */

public class LRUCache{
    private int capacity;
    private Map<Integer, Node> data;

    // used frequency ascend from head to tail
    private Node dummyHead = new Node();
    private Node dummyTail = new Node();

    public LRUCache(int capacity) {
        if(capacity < 1){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.data = new HashMap<>();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    /**
     * O(1)
     * return val if key exist, else -1
     */
    public int get(int key){
        if(data.containsKey(key)){
            Node node = data.get(key);

            moveToTail(node);

            return node.val;
        }
        return -1;
    }

    /**
     * O(1)
     */
    public void put(int key, int value){
        if(data.containsKey(key)){
            Node node = data.get(key);
            node.val = value;
            moveToTail(node);
        }else{
            Node node = new Node(key, value);
            data.put(key, node);
            setAsTail(node);
            if(data.size() > capacity){
                Node head = dummyHead.next;
                data.remove(head.key);
                dropNode(head);
            }
        }
    }
    /**
     * move the node to the tail of linked list where the most recently used node locates
     */
    private void moveToTail(Node node) {
        if(node.next != dummyTail){
            dropNode(node);
            setAsTail(node);
        }
    }

    private void dropNode(Node node) {
        Node prev = node.prev, next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void setAsTail(Node node) {
        Node prev = dummyTail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = dummyTail;
        dummyTail.prev = node;
    }

    static class Node{
        Node prev;
        Node next;
        int val;
        int key;
        Node(){

        }
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void printAccessNode(){
        System.out.println("AccessNode");
        Node temp = dummyHead.next;
        while(temp != null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 0);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);

        cache.printAccessNode();
    }
}

