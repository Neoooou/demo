package com.example.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯
 *  找执行逻辑之间关系， 停止条件
 *  大化小，小化了
 */
public class ParenthesisCombination {
    final static String left_parenthesis = "(";
    final static String right_parenthesis = ")";

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        generate(ans, "", 0, 0, n);
        return ans;
    }

    public void generate(List<String> ans, String s, int l, int r, int n){
        if(l == r && l == n){
            ans.add(s);
            return;
        }
        if(l > r){
            generate(ans, s + right_parenthesis, l, r+1, n);
        }
        if(l < n){
            generate(ans, s + left_parenthesis, l+1, r, n);
        }

    }
}
}
