package com.leetcode;

/*
https://leetcode.com/problems/continuous-subarray-sum/
Medium. Array, Hash Table, Math, Prefix Sum.

Given an integer array nums and an integer k, return true
if nums has a continuous subarray of size at least two whose elements
sum up to a multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n
such that x = n * k. 0 is always a multiple of k.

Example 1:
 Input: nums = [23,2,4,6,7], k = 6
 Output: true
 Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
 Input: nums = [23,2,6,4,7], k = 6
 Output: true
 Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
  42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
 Input: nums = [23,2,6,4,7], k = 13
 Output: false

Constraints:
 1 <= nums.length <= 10^5
 0 <= nums[i] <= 10^9
 0 <= sum(nums[i]) <= 2^31 - 1
 1 <= k <= 2^31 - 1
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @see _525_ContiguousArray
 * @see _560_SubarraySumEqualsK
 */
class _523_ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2 || nums.length > 1e5 || k < 1) return false;

        // Math proof: (prefixSums[i] - prefixSums[j]) % k == 0
        //             <=> (prefixSums[i] % k == prefixSums[j] % k)
        final Map<Integer, Integer> remainders = new HashMap<>();
        // another way of handling the 0 remainder
        //remainders.put(0, -1);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            prefixSum += num;
            int val = prefixSum % k;
            // 0 is always a multiple of k
            if (val == 0 && i >= 1) return true;

            if (remainders.containsKey(val)) {
                if (i - remainders.get(val) >= 2) return true;
                // else do nothing
            } else {
                remainders.put(val, i);
            }
        }
        return false;
    }
}
