package com.example.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯
 *  找执行逻辑之间关系， 停止条件
 *  大化小，小化了
 * @author neo.zr
 * @since 2023/8/28
 */

public class GenerateParentheses {
    final static String LEFT_PARENTHESES = "(";
    final static String RIGHT_PARENTHESES = ")";

    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> candidates = new LinkedList<>();
        backtrack(candidates, "", 0, 0, n);
        return candidates;
    }

    public void backtrack(List<String> candidates, String s, int left, int right, int n) {
        if (s.length() == 2 * n) {
            candidates.add(s);
            return;
        }
        if (left < n) {
            backtrack(candidates, s + LEFT_PARENTHESES, left + 1, right, n);
        }
        if (right < left) {
            backtrack(candidates, s + RIGHT_PARENTHESES, left, right + 1, n);
        }
    }
}
