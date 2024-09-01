package com.example.algorithm.leetcode;

public class LC33 {

    public LC33() {
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[left] < nums[mid]) {
                // 左边有序
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边有序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
