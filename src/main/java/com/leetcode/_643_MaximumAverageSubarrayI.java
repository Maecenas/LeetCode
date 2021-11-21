package com.leetcode;

/*
https://leetcode.com/problems/maximum-average-subarray-i/
Easy. Array.

Given an array consisting of n integers, find the contiguous subarray
of given length k that has the maximum average value.
And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

Note:

1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
*/

class _643_MaximumAverageSubarrayI {

    public static double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 1) return 0.0;

        double sum = 0;
        // Sliding window of size k
        int i = 0;
        while (i < k) {
            sum += nums[i];
            i++;
        }
        double res = sum;

        while (i < nums.length) {
            sum += (nums[i] - nums[i - k]);
            res = Math.max(res, sum);
            i++;
        }
        return res / k;
    }
}
