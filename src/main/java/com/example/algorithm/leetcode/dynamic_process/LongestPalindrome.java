package com.example.algorithm.leetcode.dynamic_process;

/**
 * 最长回文字串
 * 动态规划: 子串【i， j】 是回文串的条件：
 *  - i == j，就是一个字符，必然是回文
 *  - i +1 == j， 且 charAt(i) == charAt(j)
 *  - i + 1 < j 时， charAt(i) == charAt(j) 且字串【i+1，j-1】也是回文串
 * 时间复杂度 O(n^2)
 *
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        new LongestPalindrome().longestPalindrome("cbbd");
    }
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
