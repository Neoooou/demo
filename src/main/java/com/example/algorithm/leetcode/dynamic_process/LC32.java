package com.example.algorithm.leetcode.dynamic_process;

import java.util.Stack;

/**
 * 最长有效括号字串长度
 *
 * 解：动态规划
 * 当前位置最长字串长度 = （有效括号终点 - 起点 + 1） + 起点位置最长有效括号长度
 */
public class LC32 {
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


    private boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(new LC32().longestValidParentheses("()(())"));
    }
}
