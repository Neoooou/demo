package com.example.tut.basics;


import java.util.*;

/**
 * @author neo.zr
 * @since 2023/4/24
 */

public class PListDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<Integer>();
        // time complexity O(log(N))
        queue.offer(2);

        // time complexity O(1)
        Integer peek = queue.peek();

        // time complexity O(log(N))
        Integer poll = queue.poll();
        // time complexity O(N)
        queue.remove(2);


        System.out.println(queue.poll());

        int i =6, j = 1;
        double m = (i + j) / 2.0D;
        System.out.println(m);
    }

    public static long maximumQuality(List<Integer> packets, int channels) {
        // Write your code here
        packets.sort(Comparator.comparingInt(e -> e));
        List<Long> ans = new ArrayList<>();
        maximum(packets, channels, 0, 0.0D, ans);
        return ans.stream().mapToLong(e->e).max().getAsLong();
    }

    private static void maximum(List<Integer> packets, int channels, int begin, double sum, List<Long> ans){
        if(channels == 1){
            double mid = getMid(packets, begin, packets.size()-1);
            ans.add(Math.round(mid + sum));
            return;
        }

        if(packets.size() == (begin + channels)){
            ans.add(Math.round(sum + packets.stream().mapToInt(e->e).sum()));
            return;
        }

        for(int i = begin; i < packets.size(); ++i){
            double mid = getMid(packets, begin, i);
            maximum(packets, channels-1, i+1, sum+mid, ans);
        }
    }

    private static double getMid(List<Integer> packets, int begin, int end) {
        double mid;
        int step = end - begin + 1;
        if(step % 2 == 0){
            // even
            mid = (packets.get(begin + step / 2) + packets.get(begin + step / 2 - 1)) / 2.0D;
        }else{
            mid = packets.get(begin + step / 2);
        }
        return mid;
    }


    /*
     * Complete the 'findMaxProducts' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY products as parameter.
     */

    public static long findMaxProducts(List<Integer> products) {
        // Write your code here
        if(products == null || products.size() < 1){
            return 0L;
        }

        int left = 0, right = products.size() - 1;
        long max = 0;
        return 1L;

    }


}
