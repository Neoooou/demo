package com.example.algorithm.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 */
public class LC35 {
    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length < 1){
            return 0;
        }

        int left = 0 , right = nums.length - 1, middle;
        while(left <= right){
            middle = left + (right - left) / 2;
            if(target > nums[middle]){
                left = middle + 1;
            }else if (target < nums[middle]){
                right = middle - 1;
            }else{
                return middle;
            }
        }
        return left;
    }
}
