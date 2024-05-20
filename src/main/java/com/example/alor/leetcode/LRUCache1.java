package com.example.alor.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neo.zr
 * @since 2024/4/22
 */

public class LRUCache1 {

    int capacity;
    Map<Integer, Node> cache;

    Node fakeHead;
    Node fakeTail;

    public LRUCache1(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>();
        fakeHead = new Node();
        fakeTail = new Node();
        fakeHead.setNext(fakeTail);
        fakeTail.setPre(fakeHead);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.setVal(value);

            if (!node.getNext().equals(fakeTail)) {
                unlink(node);
                setAsTail(node);
            }

            cache.put(key, node);
        } else {
            Node node = new Node();
            node.setVal(value);
            node.setKey(key);
            setAsTail(node);
            cache.put(key, node);
            if (cache.size() > capacity) {
                Node toRemove = fakeHead.getNext();
                cache.remove(toRemove.getKey());
                unlink(toRemove);
            }
        }
    }

    private void setAsTail(Node node) {
        fakeTail.getPre().setNext(node);
        node.setPre(fakeTail.getPre());

        fakeTail.setPre(node);
        node.setNext(fakeTail);
    }

    private void unlink(Node node) {
        node.getPre().setNext(node.getNext());
        node.getNext().setPre(node.getPre());
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            if (!fakeTail.equals(node.getNext())) {
                unlink(node);
                setAsTail(node);
            }
            return node.getVal();
        } else {
            return -1;
        }
    }


    static class Node {
        Node pre;
        Node next;

        int val;

        int key;

        public Node() {
        }

        public Node getPre() {
            return this.pre;
        }

        public Node getNext() {
            return this.next;
        }

        public int getVal() {
            return this.val;
        }

        public int getKey() {
            return this.key;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setKey(int key) {
            this.key = key;
        }


        public String toString() {
            return "LRUCache1.Node(pre=" + this.getPre() + ", next=" + this.getNext() + ", val=" + this.getVal() + ", key=" + this.getKey() + ")";
        }
    }
}
