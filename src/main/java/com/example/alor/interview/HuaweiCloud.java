package com.example.alor.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo.zr
 * @since 2023/8/13
 */

public class HuaweiCloud {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case675
            int num = in.nextInt();
            System.out.print(convert(num) + " ");
        }
    }

    private static String convert(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;
            int curr = n%26;
            n /= 26;
            sb.append((char)(curr+'A'));
        }
        return sb.reverse().toString();
    }
}
