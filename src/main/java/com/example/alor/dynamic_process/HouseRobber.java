package com.example.alor.dynamic_process;

import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2024/4/19
 */

public class HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len+1];
        if(len < 2){
            return nums[0];
        }
        dp[1] = nums[0];
        for(int i = 2; i < len+1; ++i){
            // rob this house vs not rob this house
            dp[i] = Math.max(nums[i-1] + dp [i-2], dp[i-1]);
        }
        return dp[len];
    }

    /**
     * house are arranged in circle
     */
    public int rob1(int[] nums) {
        return Math.max(
                rob(Arrays.copyOfRange(nums, 1, nums.length)),
                rob(Arrays.copyOfRange(nums, 0, nums.length-1))
        );
    }

    public static void main(String[] args) {
        new HouseRobber().rob(new int[2]);
    }
}
