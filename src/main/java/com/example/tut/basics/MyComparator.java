package com.example.tut.basics;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * @author: neo.zr
 * @since: 2021/1/5
 */

public class MyComparator {

    public static void main(String[] args) {
        Comparator<Entity> comp = Comparator.comparing(Entity::getId).reversed();
        Comparator<Entity> comp1 = (e0, e1) ->e0.getId() - e1.getId();
        List<Entity> l = Lists.newArrayList(new Entity(1, "jack", 22.2D), new Entity(2, "tommy", 33.3D), new Entity(3, "mary", 44.4D));
        l.sort(comp);
        System.out.println(l);
        l.sort(comp1);
        System.out.println(l);

        testTry();
    }

    /**
     * finally 代码块无论如何都会执行
     */
    public static void testTry(){
        try{
            return;
        }finally {
            System.out.println("finally");
        }
    }
}

