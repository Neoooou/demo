package com.example.tutorial.guava;

import com.google.common.base.Joiner;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/3
 */

public class FileOperation {

    public static void main(String[] args) throws IOException {
//        List<String> lines = Files.readLines(new File("test.txt"), Charset.defaultCharset());

        String ipv4 = "0.123.32.255";
        String encoded = encodeIpv4(ipv4);
        System.out.println(encoded);
        System.out.println(decodeIpv4(encoded));
    }

    private static String encodeIpv4(String ipv4) {
        String[] nums = ipv4.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append(convert(Integer.parseInt(num)));
        }
        return sb.toString();
    }

    private static String decodeIpv4(String encodedStr) {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            strs.add(convert(encodedStr.substring(i * 2, i*2 + 2)));
        }
        return Joiner.on(".").join(strs);
    }


    private static char[] m = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String convert(String hex) {
        int num = 0;
        for (int i = 0; i < hex.length(); ++i) {
            int multiplier = 1;
            for (int j = i + 1; j < hex.length(); ++j) {
                multiplier *= 16;
            }
            num += idx(hex.charAt(i)) * multiplier;
        }
        return num + "";
    }

    private static int idx(char c) {
        for (int i = 0; i < m.length; ++i) {
            if (c == m[i]) {
                return i;
            }
        }
        return -1;
    }

    private static String convert(int num) {
        StringBuilder s = new StringBuilder();
        while (num > 0) {
            s.append(m[num % 16]);
            num = num / 16;
        }
        while (s.length() < 2) {
            s.append('0');
        }
        return s.reverse().toString();
    }
}
