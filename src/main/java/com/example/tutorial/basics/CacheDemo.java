package com.example.tutorial.basics;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: neo.zr
 * @since: 2021/4/6
 */

public class CacheDemo {

    LoadingCache<String, Object> cache = CacheBuilder.
            newBuilder().
            concurrencyLevel(1).
            expireAfterWrite(100, TimeUnit.MINUTES).
            build(new CacheLoader<String, Object>() {

                @Override
                public Object load(String key) throws Exception {
                    return key;
                }
            });

    public static void main(String[] args) {
        List<List<String>> listList = Lists.newArrayList();
        List<String> l = Lists.newArrayList();
        l.add("a");
        listList.add(l);
        System.out.println(listList);
        System.out.println(l);



        l.remove(0);
        System.out.println(listList);
    }
}
