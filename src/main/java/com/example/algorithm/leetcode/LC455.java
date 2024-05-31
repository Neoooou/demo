package com.example.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2024/1/29
 */

public class LC455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while(i < g.length && j < s.length){
           if(g[i] <= s[j]){
               i++;
               j++;
           }else{
               j++;
           }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] g = new int[]{3, 2, 1, 4, 5};
        Arrays.sort(g);
        System.out.println(Arrays.toString(g));
    }
}
