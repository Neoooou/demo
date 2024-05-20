package com.example.alor.leetcode;


/**
 * @author neo.zr
 * @since 2024/1/29
 */

public class LC443 {
    public int compress(char[] chars) {
        if (chars == null || chars.length < 1) {
            return 0;
        }
        int l = 0, r = 1, i = 0, c;
        String s;
        for (; r <= chars.length; ++r) {
            if (r == chars.length || chars[r] != chars[l]) {
                chars[i++] = chars[l];
                c = (r - l);
                if (c > 1) {
                    s = c + "";
                    for (int j = 0; j < s.length(); ++j) {
                        chars[i++] = s.charAt(j);
                    }
                }

                l = r;
            }
        }
        return i;
    }
}
