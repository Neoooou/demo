package com.example.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 双指针，通过左右指针的滑动解决问题
 * @Author: ranz
 * @Since: 2025/4/23
 */
public class TwoPointer {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while(left < right){
            maxArea = Math.max(maxArea, (right-left) * Math.min(height[left], height[right]));

            if(height[left] > height[right]){
                right --;
            }else{
                left ++;
            }
        }
        return maxArea;
    }


    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        Map<Character, Integer> char2Pos = new HashMap<>();
        for(;right < s.length(); ++right){
            if(char2Pos.containsKey(s.charAt(right)) && char2Pos.get(s.charAt(right)) >= left){
                left = char2Pos.get(s.charAt(right)) + 1;
            }else{
                maxLen = Math.max(maxLen, right-left+1);
            }

            char2Pos.put(s.charAt(right), right);
        }
        return maxLen;
    }
}
