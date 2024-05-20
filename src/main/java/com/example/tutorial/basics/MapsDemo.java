package com.example.tutorial.basics;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * @author: neo.zr
 * @since: 2021/1/16
 */

public class MapsDemo {
    public static void main(String[] args) {
        final Map<String, Integer> map = Maps.newHashMap();


        for(String s: "abcdefgabc".split("")){
            map.compute(s, (k, v)->v==null? 1: v+1);
        }
        System.out.println(map);

        Multimap<String, Integer> mm = ArrayListMultimap.create();
        mm.put("a", 1);
        mm.put("a", 2);
        mm.put("a", 3);
        mm.put("b", 0);
        mm.put("c", 5);
        Iterator it = mm.keySet().iterator();
        while(it.hasNext()){
            int i = 0;
            String k = it.next().toString();
            while(i < mm.get(k).size()){
                List l = new ArrayList(mm.get(k));
                System.out.println(k + " - " +  l.get(i++));
            }
        }

        System.out.println(Arrays.toString("1".split(",")));

    }
}
