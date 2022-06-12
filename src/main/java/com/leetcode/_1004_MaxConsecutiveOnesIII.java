package com.leetcode;

/*
https://leetcode.com/problems/max-consecutive-ones-iii/
Medium. Array, Binary Search, Sliding Window, Prefix Sum.

Given a binary array nums and an integer k, return the maximum number of
consecutive 1's in the array if you can flip at most k 0's.

Example 1:
 Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 Output: 6
 Explanation: [1,1,1,0,0,1,1,1,1,1,1]
  Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
 Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 Output: 10
 Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
  Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:
 1 <= nums.length <= 10^5
 nums[i] is either 0 or 1.
 0 <= k <= nums.length
*/

/**
 * @see _485_MaxConsecutiveOnes
 * @see _487_MaxConsecutiveOnesII
 */
class _1004_MaxConsecutiveOnesIII {

    public static int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length > 1e5
                || k < 0 || k > nums.length) return -1;

        // sliding window of nums[i:j) that contains at most k zeros
        int i = 0, j = 0, zeros = 0, res = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                zeros++;
            }
            j++;
            while (i < j && zeros > k) {
                if (nums[i] == 0) zeros--;
                i++;
            }
            res = Math.max(res, j - i);
        }

        return res;
    }
}
