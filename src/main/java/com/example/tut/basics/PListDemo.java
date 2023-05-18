package com.example.tut.basics;

import java.util.PriorityQueue;

/**
 * @author neo.zr
 * @since 2023/4/24
 */

public class PListDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(2);
        queue.offer(1);
        queue.offer(5);
        queue.offer(3);
        System.out.println(queue.poll());
    }
}
