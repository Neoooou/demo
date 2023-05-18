package com.example.tut.solutions;

import com.google.common.collect.Lists;

import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author neo.zr
 * @since 2023/2/8
 */


public class Solution {

    /**
     * 合并两个有序的单向链表 [默认降序], 去掉重复元素
     */
    private Node mergeSortedLinkedList(Node first, Node second) {
        Node dummy = new Node(Integer.MIN_VALUE);
        Node temp = dummy;
        Node next = null;
        Node tempFirst = first;
        Node tempSecond = second;
        while (tempFirst != null || tempSecond != null) {
            if (tempFirst == null) {
                next = tempSecond;
                tempSecond = tempSecond.next;
            } else if (tempSecond == null) {
                next = tempFirst;
                tempFirst = tempFirst.next;
            } else {
                if (tempFirst.val > tempSecond.val) {
                    next = tempFirst;
                    tempFirst = tempFirst.next;
                } else {
                    next = tempSecond;
                    tempSecond = tempSecond.next;
                }
            }
            if (!temp.val.equals(next.val)) {
                temp.next = next;
                temp = next;
            }
        }

        return dummy.next;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> m = new HashMap();
        Map<Character, Integer> uniques = new HashMap<>();
        for (int i = 0; i < (s.length() - minSize); ++i) {
            String subS = s.substring(i, i + minSize);
            if (i == 0) {
                for (int j = 0; j < subS.length(); ++j) {
                    uniques.compute(subS.charAt(j), (k, v) -> v == null ? 1 : v + 1);
                }
            } else {
                uniques.compute(subS.charAt(subS.length() - 1), (k, v) -> v == null ? 1 : v + 1);
                Character removeC = s.charAt(i - 1);
                uniques.compute(removeC, (k, v) -> v - 1);
                if (uniques.get(removeC) == 0) {
                    uniques.remove(removeC);
                }
            }
            if (uniques.size() > maxLetters) {
                continue;
            }
            m.compute(subS, (k, v) -> v == null ? 1 : v + 1);
        }
        return m.values().stream().max(Comparator.comparingInt(e -> e)).orElse(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Set<Integer> sets = new HashSet<>();
        sets.add(3);
        sets.add(1);
        sets.add(2);

        List<Integer> l =
                sets.stream().sorted(Comparator.comparingInt(e -> e)).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println(l);
        System.out.println(Arrays.toString("abc".split("")));

        System.out.println(Boolean.TRUE.toString());

        System.out.println(Lists.newArrayList(1, 1, 2, 2, 3, 3, 3).stream().sorted(Comparator.comparingInt(e -> (int) e).reversed())
                .limit(2).collect(Collectors.toList()));

        int[] charCount = new int[26];

        System.out.println(Arrays.toString(charCount));


        double d1 = Math.log(0.94);
        System.out.println(d1);
        System.out.println(Math.log(0.94)+Math.log(0.01)+Math.log(0.94));

        int i = 1;
        System.out.println(i-- > 0 );
        i = 1;
        System.out.println(--i > 0 );
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int top = 0;
        for (int i = 0; i < nums.length; ++i) {
            count.compute(nums[i], (kk, v) -> v == null ? 1 : v + 1);
        }
        List<Integer> topKCount = count.values().stream().sorted(Comparator.comparingInt(e -> (int) e).reversed())
                .limit(k).collect(Collectors.toList());
        int[] ret = new int[k];
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (topKCount.contains(entry.getValue())) {
                ret[--k] = entry.getKey();
                if (k == 0) {
                    break;
                }
            }
        }
        return ret;
    }

    public int findLongestChain(int[][] pairs) {
        for (int i = 0; i < pairs.length; ++i) {
            for (int j = i + 1; j < pairs.length; ++j) {
                if (pairs[i][0] > pairs[j][0]) {
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }

        int size = 1;
        int idx = 0;
        for (int i = 1; i < pairs.length; ++i) {
            if (pairs[i][0] > pairs[idx][1]) {
                size++;
                idx = i;
            } else {
                if (pairs[i][1] < pairs[idx][1]) {
                    idx = i;
                }
            }
        }

        return size;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = lessEqual(matrix, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;

    }

    //from left bottom or right top we can count how many numbers are equal or less than our target
    public int lessEqual(int[][] matrix, int target) {
        int count = 0, len = matrix.length, i = len - 1, j = 0;

        while (i >= 0 && j < len) {
            if (matrix[i][j] > target) {
                i--;
            } else {
                count = count + i + 1;
                j++;
            }
        }
        return count;
    }

    /**
     * return true if one of s1' permutations is a sub string of s2
     */
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s1.length() < 1){
            return false;
        }
        if(s2 == null || s2.length() < s1.length()){
            return false;
        }

        int[] s1Freq = new int[26];
        for(int i = 0; i < s1.length(); ++i){
            s1Freq[s1.charAt(i) - 'a']++;
        }

        int s1UniCharCount = 0;
        for(int i = 0; i < s1Freq.length; ++i){
            if(s1Freq[i] > 0){
                s1UniCharCount++;
            }
        }

        int left = 0, right = 0, winUniCharCount = 0;
        int[] winFreq = new int[26];
        int charIdx;
        /**
         * 滑动窗口，统计窗口内的唯一字符数，
         */
        while(right < s2.length()){
            charIdx = s2.charAt(right) - 'a';
            if(s1Freq[charIdx] > 0){
                winFreq[charIdx]++;
                if(winFreq[charIdx] == s1Freq[charIdx]){
                    winUniCharCount++;
                }
            }
            right++;
            while(winUniCharCount == s1UniCharCount){
                if(right - left == s1.length()){
                    return true;
                }
                charIdx = s2.charAt(left) - 'a';
                if(s1Freq[charIdx] > 0){
                    winFreq[charIdx]--;
                    if(winFreq[charIdx] < s1Freq[charIdx]){
                        winUniCharCount--;
                    }
                }
                left++;
            }
        }

        return false;
    }

    public int maxProfit(int[] prices) {
        int minPrice = prices[0], maxProfit = 0;

        for(int i = 1; i < prices.length; ++i){
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }

    public int fibonacci(int n) {
        if(n < 2){
            return n;
        }

        int p = 0, q = 1, t;
        while(--n > 1){
            t = q;
            q = p + q;
            p = t;
        }

        return q;
    }


}
