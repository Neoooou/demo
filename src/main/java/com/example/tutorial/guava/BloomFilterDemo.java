package com.example.tutorial.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @Author: ranz
 * @Since: 2025/3/18
 */
public class BloomFilterDemo {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000);

        boolean isExist = bloomFilter.put("abc");
        System.out.println(isExist);

        isExist = bloomFilter.put("abc");
        System.out.println(isExist);

    }
}
