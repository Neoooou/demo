package com.example.algorithm.leetcode;

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
        if(n < 1){
            return 0;
        }
        return -1;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        Set<Character> alphas = new HashSet<Character>();
        for(int i =0; i < word.length(); ++i){
            alphas.add(word.charAt(i));
        }

        for(int i = 0; i < m; ++i){
            for(int j=0;j < n; ++j){
                if(board[i][j] == word.charAt(0)){

                }
            }
        }

        return false;
    }


    private boolean find(char[][] board, String word, int i, int j){
        return false;
    }



}
