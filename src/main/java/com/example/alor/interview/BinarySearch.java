package com.example.alor.interview;

/**
 * @author neo.zr
 * @since 2024/4/9
 */

public class BinarySearch {

    /**
     * nums: sorted array
     * target: search target
     */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};

        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                res[0] = mid;
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                res[1] = mid;
                left = mid + 1;
            }
        }
        return res;
    }

    public int searchInRotate(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[right] >= nums[mid]){
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{
                if(nums[mid] > target && target >= nums[left]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
