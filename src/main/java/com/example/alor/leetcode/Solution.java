package com.example.alor.leetcode;

import com.example.entity.SinglyLinkedNode;

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
    private SinglyLinkedNode mergeSortedLinkedList(SinglyLinkedNode first, SinglyLinkedNode second) {
        SinglyLinkedNode dummy = new SinglyLinkedNode(Integer.MIN_VALUE);
        SinglyLinkedNode temp = dummy;
        SinglyLinkedNode next = null;
        SinglyLinkedNode tempFirst = first;
        SinglyLinkedNode tempSecond = second;
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
        if (s1 == null || s1.length() < 1) {
            return false;
        }
        if (s2 == null || s2.length() < s1.length()) {
            return false;
        }

        int[] s1Freq = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            s1Freq[s1.charAt(i) - 'a']++;
        }

        int s1UniCharCount = 0;
        for (int i = 0; i < s1Freq.length; ++i) {
            if (s1Freq[i] > 0) {
                s1UniCharCount++;
            }
        }

        int left = 0, right = 0, winUniCharCount = 0;
        int[] winFreq = new int[26];
        int charIdx;
        /**
         * 滑动窗口，统计窗口内的唯一字符数，
         */
        while (right < s2.length()) {
            charIdx = s2.charAt(right) - 'a';
            if (s1Freq[charIdx] > 0) {
                winFreq[charIdx]++;
                if (winFreq[charIdx] == s1Freq[charIdx]) {
                    winUniCharCount++;
                }
            }
            right++;
            while (winUniCharCount == s1UniCharCount) {
                if (right - left == s1.length()) {
                    return true;
                }
                charIdx = s2.charAt(left) - 'a';
                if (s1Freq[charIdx] > 0) {
                    winFreq[charIdx]--;
                    if (winFreq[charIdx] < s1Freq[charIdx]) {
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

        for (int i = 1; i < prices.length; ++i) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }

    public int fibonacci(int n) {
        if (n < 2) {
            return n;
        }

        int p = 0, q = 1, t;
        while (--n > 1) {
            t = q;
            q = p + q;
            p = t;
        }

        return q;
    }

    /**
     * 424. Longest Repeating Character Replacement
     */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0, right = 0, max = 0, mostFreq = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            mostFreq = Math.max(mostFreq, freq[c - 'A']);

            if ((right - left - mostFreq) > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }


    public int solution(int[] A) {
        // Implement your solution here
        Arrays.sort(A);
        int min = 1;
        for (int i = 1; i < A.length; ++i) {
            if (A[i - 1] + 1 == A[i] || A[i] == A[i - 1]) {
                continue;
            }
            return Math.min(min, A[i - 1] + 1);
        }
        return Math.max(min, A[A.length - 1] + 1);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] flags = new Boolean[s.length() + 1];
        Set<String> wordSet = new HashSet<>(wordDict);
        flags[0] = true;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                if (flags[j] && wordSet.contains(s.substring(j, i + 1))) {
                    flags[i + 1] = true;
                }
            }
        }
        return flags[s.length()];
    }

    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        Boolean[] isPrime = new Boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < n; ++i) {
            for (int j = 2 * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 0; i < n + 1; ++i) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

    private static void search(String numsLine, String target) {
        if (numsLine == null || numsLine.length() < 1 || target == null || target.length() < 1) {
            System.out.println("0 0 0");
            return;
        }

        List<Long> nums;
        Long targetLong;
        try {
            nums = Arrays.stream(numsLine.split(" ")).map(s -> Long.parseLong(s)).collect(Collectors.toList());
            targetLong = Long.parseLong(target);
        } catch (Exception e) {
            System.out.println("0 0 0");
            return;
        }

        search(nums, targetLong);
    }

    private static void search(List<Long> nums, Long target) {
        if (nums == null || nums.isEmpty()) {
            System.out.println("0 0 0 ");
            return;
        }
        int left = 0, right = nums.size() - 1;
        Long sum;
        while (left < right) {
            sum = nums.get(left) + nums.get(right);
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                System.out.println("1 " + left + " " + right);
                return;
            }
        }
        System.out.println("0 0 0 ");
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
//        int[] dp = new int[nums.length + 1];
//        dp[0] = 0;
        int prev = 0;
//        dp[1] = nums[0];
        int curr = nums[0];
        int next = 0;
        for (int i = 2; i <= nums.length; ++i) {
//            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            next = Math.max(curr, prev + nums[i - 1]);
            prev = curr;
            curr = next;
        }
        return next;
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> sibling = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (!sibling.containsKey(num)) {
                int left = sibling.getOrDefault(num - 1, 0);
                int right = sibling.getOrDefault(num + 1, 0);
                max = Math.max(max, left + right + 1);

                sibling.put(num, max);

                sibling.put(num - left, max);
                sibling.put(num + right, max);
            }
        }

        return max;
    }

    static Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(2, "abc");
        put(3, "def");
        put(4, "ghi");
        put(5, "jkl");
        put(6, "mno");
        put(7, "pqrs");
        put(8, "tuv");
        put(9, "wxyz");
    }};


    public List<String> letterCombinations(String digits) {
        return letterCombinations(digits, Collections.EMPTY_LIST);
    }

    public List<String> letterCombinations(String digits, List<String> strs) {
        if (digits == null || digits.length() < 1) {
            return strs;
        }
        int num = Integer.parseInt(digits.charAt(0) + "");
        List<String> res = new LinkedList<>();
        for (String letter : map.get(num).split("")) {
            if (strs.size() < 1) {
                res.add(letter);
            } else {
                for (String str : strs) {
                    res.add(str + letter);
                }
            }

        }
        return letterCombinations(digits.substring(1), res);
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new boolean[10]));
    }

}
