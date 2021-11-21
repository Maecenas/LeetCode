package com.leetcode;

/*
https://leetcode.com/problems/maximum-subarray/
Easy. Array, Divide and Conquer, Dynamic Programming.

Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution,
try coding another solution using the divide and conquer approach, which is more subtle.
*/

/**
 * @see _152_MaximumProductSubarray
 * @see _918_MaximumSumCircularSubarray
 * @see _978_LongestTurbulentSubarray
 */
class _53_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;

        int res = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum = Math.max(sum, 0) + num;
            res = Math.max(res, sum);
        }
        return res;
    }

    public static int maxSubArrayDivideAndConquer(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;

        return maxSubArray(nums, 0, nums.length - 1);
    }

    private static int maxSubArray(final int[] nums, final int lo, final int hi) {
        if (lo > hi) return Integer.MIN_VALUE;
        if (lo == hi) return nums[lo];

        final int mid = lo + ((hi - lo) >> 1);
        final int L = maxSubArray(nums, lo, mid - 1);
        final int R = maxSubArray(nums, mid + 1, hi);

        int currSum = 0;
        int leftSum = 0;
        for (int i = mid - 1; i >= lo; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }
        currSum = 0;
        int rightSum = 0;
        for (int i = mid + 1; i <= hi; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }
        return Math.max(Math.max(L, R), leftSum + rightSum + nums[mid]);
    }
}
