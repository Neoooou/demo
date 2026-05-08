package com.example.tutorial;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Neo.zr
 * @Since: 2026/3/18
 **/
public class Sc {
    public static void main(String[] args) {
        Random random = new Random();
        Stopwatch stopwatch = Stopwatch.createStarted();
        NavigableMap<Integer, String> map = Maps.newTreeMap();
        for(int i = 0; i < 10_000_000; i++) {
            int val = random.nextInt(100_000_000);
            map.put(val, String.valueOf(val));
        }
        System.out.println("Init Navigable Map(size: 100M) cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        stopwatch.reset().start();

        Map<Integer, String> map2 = Maps.newHashMap();
        for(int i = 0; i < 10_000_000; i++) {
            int val = random.nextInt(100_000_000);
            map2.put(val, String.valueOf(val));
        }

        System.out.println("Init Hash Map(size: 100M) cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");

        stopwatch.reset().start();
        Map.Entry<Integer, String> mapEntry = map.higherEntry(5000);
        System.out.println("Found In Navigate " + mapEntry.getKey() + "cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");


        stopwatch.reset().start();
        Integer found = null;
        for(Integer key : map2.keySet()) {
            if(key > 5000) {
                found = found == null? key : Math.min(found, key);
            }
        }

        System.out.println("Found in HashMap " + found + "cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");


    }
}
