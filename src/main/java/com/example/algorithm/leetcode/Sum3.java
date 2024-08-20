package com.example.algorithm.leetcode;

import com.google.common.base.Functions;

import java.util.*;
import java.util.stream.Collectors;

public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if(nums.length < 3){
            return Collections.EMPTY_LIST;
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; ++i){
            int target = -nums[i];
            int j = i + 1, k = n -1;
            for(;j < n; j++){
                if(j > i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                while(j < k && nums[j]+nums[k]>target){
                    k--;
                }
                if(j==k){
                    break;
                }
                if(nums[j]+nums[k] == target){
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    res.add(l);
                }
            }

        }
        return res;
    }
}