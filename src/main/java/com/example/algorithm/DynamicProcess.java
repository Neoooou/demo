package com.example.algorithm;

import java.util.Arrays;
import java.util.Stack;

/**
 * 动态规划，从前至后求解，寻找转化方程。
 * @Author: ranz
 * @Since: 2024/12/5
 */
public class DynamicProcess {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1; i < len1+1; ++i){
            dp[i][0] = i;
        }
        for(int i = 1; i < len2+1; ++i){
            dp[0][i] = i;
        }
        for(int i = 1; i < len1+1; ++i){
            for(int j = 1; j < len2+1; ++j){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * LeetCode 64,
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if(grid == null){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] pathSum = new int[rows][cols];
        pathSum[0][0] = grid[0][0];
        for (int row = 1; row < rows; row++) {
            pathSum[row][0] = grid[row][0] + pathSum[row - 1][0];
        }
        for (int col = 1; col < cols; ++col) {
            pathSum[0][col] = grid[0][col] + pathSum[0][col - 1];
        }

        for (int row = 1; row < rows; ++row) {
            for (int col = 1; col < cols; ++col) {
                pathSum[row][col] = pathSum[row - 1][col] < pathSum[row][col - 1] ? pathSum[row - 1][col] + grid[row][col] : pathSum[row][col - 1] + grid[row][col];
            }
        }
        return pathSum[rows-1][cols-1];
    }

    /**
     * 房子抢劫，如果抢劫相邻的房子则会触发警报
     * rob_money(i) = max(rob_money(i -2) + house(i), rob_money(i-1))
     * @param nums
     * @return
     */
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
     * house are arranged in circle,如果房子是环形的，最后一个房子和第一个房子相邻。
     * 最大抢劫金额 = max（抢第一个到倒数第二， 抢第二个到倒数第一个）
     */
    public int robCycled(int[] nums) {
        return Math.max(
                rob(Arrays.copyOfRange(nums, 1, nums.length)),
                rob(Arrays.copyOfRange(nums, 0, nums.length-1))
        );
    }

    /**
     * LeetCode 32
     * 最长有效括号字串长度
     * 当前位置最长字串长度 = （有效括号终点 - 起点 + 1） + 起点位置最长有效括号长度
     */
    public int longestValidParentheses(String s) {
        int cur = 0, len = s.length(), maxLen = 0;
        int[] dp = new int[len +1 ];
        dp[0] = 0; // maxLen = 0 at index=-1
        Stack<Integer> stack = new Stack<>();

        while(cur < len){
            if(s.charAt(cur) == '('){
                stack.push(cur);
            }else{
                if(stack.isEmpty()){
                    // 当前栈中没有左括号。当前位置断档，最长字串长度为0，重新开始规划
                    cur++;
                    continue;
                }
                int ele = stack.pop();

                // 当前位置最长字串长度 = 从当前算起最长有效括号长度 + 前一个位置最长有效括号长度
                dp[cur+1] = cur - ele + 1 + dp[ele];

                maxLen = Math.max(dp[cur+1], maxLen);
            }
            cur++;
        }

        return maxLen;
    }

    /**
     * 最长回文字串
     * 动态规划: 子串【i， j】 是回文串的条件：
     *  - i == j，就是一个字符，必然是回文
     *  - i +1 == j， 且 charAt(i) == charAt(j)
     *  - i + 1 < j 时， charAt(i) == charAt(j) 且字串【i+1，j-1】也是回文串
     * 时间复杂度 O(n^2)
     *
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] bools = new boolean[n][n];

        for(int i = 0; i < n; ++i){
            bools[i][i] = true;
        }
        int maxLen = 1;
        String maxSub = s.charAt(0)+"";
        for(int i = n-1; i > -1; --i){
            for(int j = i+1; j < n; ++j){
                bools[i][j] = s.charAt(i) == s.charAt(j) && (j == i+1 || bools[i-1][j-1]);
                if(bools[i][j] && (j -i+1 >maxLen)){
                    maxLen = j - i +1;
                    maxSub = s.substring(i, j+1);
                }
            }
        }
        return maxSub;

    }

}
