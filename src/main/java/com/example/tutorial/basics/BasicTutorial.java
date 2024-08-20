package com.example.tutorial.basics;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

public class BasicTutorial {

    public static void main(String[] args) {
        Map<String, Integer> map = Maps.newHashMap();
        for (String s : "abcdefgabc".split("")) {
            map.compute(s, (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(String.format("key: %s, value:%d", entry.getKey(), entry.getValue()));
        }

        List<String> l = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            l.add(String.valueOf(i));
        }
        Iterator<String> it = l.iterator();
        while(it.hasNext()){
            String ele = it.next();
            if("5".equals(ele)){
                it.remove();
            }
        }
        System.out.println(l);


        Stack<String> stack = new Stack<>();
        stack.push("a");
        String peek = stack.peek();
        stack.pop();


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
