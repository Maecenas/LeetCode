package com.leetcode;

/*
https://leetcode.com/problems/contiguous-array/
Medium. Array, Hash Table, Prefix Sum.

Given a binary array nums, return the maximum length of a contiguous subarray
with an equal number of 0 and 1.

Example 1:
 Input: nums = [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
 Input: nums = [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Constraints:
 1 <= nums.length <= 10^5
 nums[i] is either 0 or 1.
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @see _523_ContinuousSubarraySum
 * @see _560_SubarraySumEqualsK
 */
class _525_ContiguousArray {

    public static int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length > 1e5) return -1;

        // If we count 0 as -1, the question is equal to: return the longest subarray that sums to 0
        int res = 0, sum = 0;
        final Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum = sum + (nums[i] == 0 ? -1 : 1);
            if (prefixSum.containsKey(sum)) {
                res = Math.max(res, i - prefixSum.get(sum));
            } else {
                prefixSum.put(sum, i);
            }
        }
        return res;
    }
}
