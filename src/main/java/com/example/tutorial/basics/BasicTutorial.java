package com.example.tutorial.basics;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.data.relational.core.sql.In;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasicTutorial {

    public static void main(String[] args) throws InterruptedException {
        /**
         * Map, key:value
         */
        Map<String, Integer> map = Maps.newHashMap();
        for (String s : "abcdefgabc".split("")) {
            map.compute(s, (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
        }

        /**
         * List
         */
        int size = 5000_000_0;
        List<String> arrayList = Lists.newArrayList();
        for (int i = 0; i < size; ++i) {
            arrayList.add(String.valueOf(i));
        }
        Iterator<String> it = arrayList.iterator();
        while(it.hasNext()){
            it.next();
            // it.remove();
        }

        List<String> linkedList = Lists.newLinkedList();
        for (int i = 0; i < size; ++i) {
            linkedList.add(String.valueOf(i));
        }
        //enhanced for loop
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(String s: arrayList){

        }
        System.out.println("Enhanced for loop, traversal array list cost time " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

        stopwatch.reset();
        for(String s:linkedList){

        }
        System.out.println("Enhanced for loop, traversal linked list cost time " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

        for(int i=0; i < arrayList.size(); ++i){
            arrayList.get(i);
        }
        System.out.println("indexed traversal array list cost time " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

        stopwatch.reset();
        for(int i=0; i< linkedList.size(); ++i){
            linkedList.get(i);
        }
        System.out.println("indexed traversal linked list cost time " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");


        /**
         * Stack, First-in-last-out
         */
        Stack<String> stack = new Stack<>();
        stack.push("a");
        String peek = stack.peek();
        stack.pop();


        /**
         * Queue, First-in-first-out
         */
        Queue<Integer> queue = new PriorityQueue<Integer>();
        // time complexity O(log(N))
        queue.offer(2);
        // time complexity O(1)
        Integer queuePeek = queue.peek();
        // time complexity O(log(N))
        Integer poll = queue.poll();
        // time complexity O(N)
        queue.remove(2);

    }
}
