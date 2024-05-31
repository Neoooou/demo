package com.example.algorithm.leetcode;
import java.util.*;


/**
 * @author neo.zr
 * @since 2024/1/16
 */

public class LC3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> occur = new HashMap<Character, Integer>();
        int len = 0, start = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (occur.containsKey(s.charAt(i))){
                start = Math.max(start, occur.get(s.charAt(i)));
            }
            len = Math.max(i - start, len);
            occur.put(s.charAt(i), i);
        }
        return len;
    }
}
