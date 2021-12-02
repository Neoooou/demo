package com.example.demo;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/17
 */

public class TestScript {
    static int TIME_INTERVAL_PER_MIN = 5;
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> m = Maps.newHashMap();

        String s = String.format("%s was write to cache with key %s", "a", "b");
        System.out.println(s);

        final double d = 0.12345D;
        System.out.println(doubleScale(d, 1));

        List<Integer> l = Lists.newArrayList(1, 2, 3, 4);
        Map<Integer, Integer> m1 = l.stream().collect(Collectors.toMap(e->e-1, Function.identity()));
        System.out.println(m1);
    }

    private static double doubleScale(double value, int scale){
        if(scale < 0){
            throw new RuntimeException("parameter scale should be positive!");
        }
        double scaleNum = Math.pow(10D, scale);
        return (double)Math.round(value*scaleNum) / scaleNum;
    }

}


