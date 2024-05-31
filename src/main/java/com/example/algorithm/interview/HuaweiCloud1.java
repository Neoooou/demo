package com.example.algorithm.interview;


import java.util.*;

/**
 * @author neo.zr
 * @since 2023/8/13
 */

public class HuaweiCloud1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        Long result = 0L;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String sec = line.substring(0, 19);
            Integer mill = Integer.parseInt(line.substring(19));

            if(map.containsKey(sec)){
                map.get(sec).add(mill);
            }else{
                List<Integer> l = new ArrayList<>();
                l.add(mill);
                map.put(sec, l);
            }
        }

        for(String k: map.keySet()){
            List<Integer> mills = map.get(k);
            final Integer min = mills.stream().min(Comparator.comparingInt(e ->e)).get();
            result += mills.stream().filter(e -> min.equals(e)).count();
        }

        System.out.println(result);
    }
}
