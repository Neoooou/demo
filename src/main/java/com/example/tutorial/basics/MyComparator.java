package com.example.tutorial.basics;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;

/**
 * @author: neo.zr
 * @since: 2021/1/5
 */

public class MyComparator {

    @ToString
    @Data
    @AllArgsConstructor
    static class A{
        int i;
        String s;

    }

    public static void main(String[] args) {
        Comparator<A> comp = Comparator.comparing(A::getI).reversed();
        Comparator<A> comp1 = (a1, a2) ->{
            return a2.getI() - a2.getI();
        };
        List<A> l = Lists.newArrayList(new A(1, "1"), new A(2, "2"), new A(3, "3"), new A(4, "4"));
        l.sort(comp);
        System.out.println(l);
        l.sort(comp1);
        System.out.println(l);
        try{
            return;
        }finally {
            System.out.println(123);
        }
    }
}

