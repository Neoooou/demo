package com.example.algorithm.leetcode;

import org.springframework.data.relational.core.sql.In;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 卡特兰数
 */
public class LC96 {
    int ans = 0;

    public int numTrees(int n) {
        if (n < 1) {
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,2,3,3};
        new LC96().removeDuplicates(nums);
    }
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 3) {
            return nums == null ? 0 : nums.length;
        }
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                nums[i - 2] = Integer.MIN_VALUE;
            }
        }

        int dupCount = 0;
        for (int i = 0; i < nums.length;++i) {
            if (nums[i] == Integer.MIN_VALUE) {
                dupCount++;
            }else{
                i = move(nums, dupCount, i);
            }
        }
        return nums.length - dupCount;
    }

    private int move(int[] nums, int moveStep, int startIdx) {
        if(moveStep < 1){
            return startIdx;
        }
        int i = startIdx;
        for (; i < nums.length && nums[i] != Integer.MIN_VALUE; ++i) {
            nums[i - moveStep] = nums[i];
        }
        return i-1;
    }


    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        Set<Character> alphas = new HashSet<Character>();
        for (int i = 0; i < word.length(); ++i) {
            alphas.add(word.charAt(i));
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == word.charAt(0)) {

                }
            }
        }

        return false;
    }


    private boolean find(char[][] board, String word, int i, int j) {
        return false;
    }


}
