package com.leetcode;

/*
https://leetcode.com/problems/minimum-size-subarray-sum/
Medium. Array, Two Pointers, Binary Search.

Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:

If you have figured out the O(n) solution, try coding another solution
of which the time complexity is O(n log n).
*/

class _209_MinimumSizeSubarraySum {

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = 0, sum = 0, res = Integer.MAX_VALUE;
        while (end < nums.length) {
            sum += nums[end];
            while (start <= end && sum >= s) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
