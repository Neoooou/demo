package com.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维矩阵顺时针打印
 */
public class LC54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        List<Integer> ans = new ArrayList<>(colLen * rowLen);
        int cycle = (Math.min(rowLen, colLen)+1) / 2;
        cycle = cycle > 0 ? cycle : 1;
        for (int c = 0; c < cycle; ++c) {
            int firstRow = c;
            for (int j = c; j < colLen - c; ++j) {
                ans.add(matrix[firstRow][j]);
            }

            int lastCol = colLen - (c+1);
            for (int row = c + 1; row < rowLen - c - 1; row++) {
                ans.add(matrix[row][lastCol]);
            }

            int lastRow = rowLen - (c + 1);
            if (lastRow > c) {
                for (int j = colLen - c - 1; j >= c; --j) {
                    ans.add(matrix[lastRow][j]);
                }
            }

            int firstCol = c;
            if(firstCol < lastCol){
                for (int row = rowLen - c - 2; row >c; --row){
                    ans.add(matrix[row][firstCol]);
                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(new LC54().spiralOrder(matrix));

        matrix = new int[][]{{7}, {8}, {9}};
        System.out.println(new LC54().spiralOrder(matrix));
    }
}
