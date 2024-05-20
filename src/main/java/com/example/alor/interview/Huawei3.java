package com.example.alor.interview;


import java.util.Scanner;

/**
 * @author neo.zr
 * @since 2023/8/27
 */

public class Huawei3 {
    final static String TAB = "    ";
    final static String SUPPLIER = "*";
    final static String EMPTY = "";


    private static String getNum(int num) {
        if (num < 1000) {
            String s = String.valueOf(num);
            return s + repeat(SUPPLIER, 4 - s.length());
        }
        return String.valueOf(num);
    }

    // 奇数行升序，偶数行降序
    private static Boolean isAscending(int row) {
        return row % 2 == 1;
    }

    /**
     * return empty if n < 1
     */
    private static String repeat(String s, int n){
        if(n < 1){
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n; ++i){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int row = 1;
        int maxNum, minNum;
        StringBuilder sb;
        while (row <= n) {
            maxNum = (1 + row) * row / 2;
            minNum = maxNum - row + 1;
            sb = new StringBuilder();

            sb.append(repeat(TAB, n -row));

            if (isAscending(row)) {
                while (minNum < maxNum) {
                    sb.append(getNum(minNum));
                    sb.append(TAB);
                    minNum ++;
                }
                sb.append(getNum(maxNum));
            } else {
                while (maxNum > minNum) {
                    sb.append(getNum(maxNum));
                    sb.append(TAB);
                    maxNum --;
                }
                sb.append(getNum(minNum));
            }
            System.out.println(sb.toString());
            row ++;
        }
    }
}
