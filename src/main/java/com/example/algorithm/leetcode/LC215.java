package com.example.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author neo.zr
 * @since 2024/1/30
 */

public class LC215 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(new LC215().findKthLargest(nums, 3));

    }

    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new LinkedList<>();
        for (int num : nums) {
            list.add(num);
        }
        return findKthLargest(list, k);
    }

    public int findKthLargest(List<Integer> nums, int k) {
        Random random = new Random();
        int idx = random.nextInt(nums.size());
        int pivot = nums.get(idx);
        List<Integer> small = new LinkedList<>(), equal = new LinkedList<>(), large = new LinkedList<>();
        for (Integer num : nums) {
            if (num > pivot) {
                large.add(num);
            } else if (num < pivot) {
                small.add(pivot);
            } else {
                equal.add(num);
            }
        }

        if (large.size() >= k) {
            return findKthLargest(large, k);
        }

        if (large.size() + equal.size() < k) {
            return findKthLargest(small, k - large.size() - equal.size());
        }
        return pivot;
    }
}
