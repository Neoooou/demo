package com.example.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author neo.zr
 * @since 2024/1/30
 */

public class LC658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length-1;
        while ((r-l+1) > k) {
            if (Math.abs(x - arr[l]) < Math.abs(x - arr[r])) {
                r--;
            } else if (Math.abs(x - arr[l]) > Math.abs(x - arr[r])) {
                l++;
            }else{
                r--;
            }
        }
        List<Integer> ans = Lists.newArrayList();
        while(l <= r){
            ans.add(arr[l++]);
        }
        return ans;
    }
}
