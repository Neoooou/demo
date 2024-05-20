package com.example.tutorial.guava;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/1
 */

public class CollectionOperation {
    public static void main(String[] args) throws InterruptedException {
        Map m = Maps.newHashMap();
        m.put("a", 1);m.put("b", 2);m.put("c", 3);
        Map m1 = Maps.newHashMap();
        m1.put("a", 1); m1.put("b", 2);m1.put("c", 3);
        MapDifference differenceMap =  Maps.difference(m ,m1);
        System.out.println(differenceMap.areEqual());
        Map entriesDiffering = differenceMap.entriesDiffering();
        Map entriesOnlyLeft = differenceMap.entriesOnlyOnLeft();
        Map entriesOnlyRight = differenceMap.entriesOnlyOnRight();
        Map entriesInCommon = differenceMap.entriesInCommon();

        System.out.println(entriesDiffering);   // {b=(2, 20)}
        System.out.println(entriesOnlyLeft);    // {a=1}
        System.out.println(entriesOnlyRight);   // {d=4}
        System.out.println(entriesInCommon);    // {c=3}

        // immutable object
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        iList.contains("a");
        List<String> l = iList;
        System.out.println("Immutable contians: \"a\" : " + iList.contains("a"));

        Set<String> set1 = Sets.newHashSet();
        set1.add("a");
        set1.add("c");

        Set<String> set2 = Sets.newHashSet();
        set2.add("b");
        set2.add("c");

        Set<String> diffSet = Sets.difference(set1, set2);
        System.out.println(diffSet);

        Set<String> interSet = Sets.intersection(set1, set2);
        System.out.println(interSet);

        Set<String> unionSet = Sets.union(set1, set2);
        System.out.println(unionSet);

        List<String> list1 = Lists.newLinkedList();

        List<String> list2 = Lists.newArrayList();
        list1.add("a");
        list1.add("b");
        list2.add("b");
        list2.add("c");

        List<List<String>> partitionList1 = Lists.partition(list1, 1);

    }
}
