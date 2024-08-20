package com.example.tutorial.basics;


import java.util.*;

/**
 * @author neo.zr
 * @since 2023/4/24
 */

public class PListDemo {

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




}
