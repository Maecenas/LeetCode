package com.leetcode;

/*
https://leetcode.com/problems/split-array-largest-sum/
Hard. Binary Search, Dynamic Programming.

Given an array nums which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

Example 1:
Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Example 2:
Input: nums = [1,2,3,4,5], m = 2
Output: 9

Example 3:
Input: nums = [1,4,4], m = 3
Output: 4

Constraints:

 1 <= nums.length <= 1000
 0 <= nums[i] <= 10^6
 1 <= m <= min(50, nums.length)
*/

class _410_SplitArrayLargestSum {

    public static int splitArray(int[] nums, int m) {
        if (nums == null || nums.length > 1000
                || m < 1 || m > 50 || m > nums.length) return -1;

        int lo = 0; int hi = 0, mid;
        for (int num : nums) {
            lo = Math.max(lo, num);
            hi += num;
        }
        if (m == 1) return hi;

        // binary search to find the smallest maxSum to split into m subarrays
        while (lo < hi) {
            mid = (lo + hi) >>> 1;
            if (canSplit(nums, m, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Given maxSum, return if the min arrays has length <= m
     */
    private static boolean canSplit(int[] nums, int m, int maxSum) {
        int count = 1;
        int sum = 0;
        for(int num : nums) {
            sum += num;
            if (sum > maxSum) {
                sum = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
