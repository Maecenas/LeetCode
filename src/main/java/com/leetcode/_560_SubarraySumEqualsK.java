package com.leetcode;

/*
https://leetcode.com/problems/subarray-sum-equals-k/
Medium. Array, Hash Table.

Given an array of integers and an integer k, you need to find
the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:

The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

import java.util.HashMap;

/**
 * @see _363_MaxSumOfRectangleNoLargerThanK
 * @see _713_SubarrayProductLessThanK
 * @see _1074_NumberOfSubmatricesThatSumToTarget
 */
class _560_SubarraySumEqualsK {

    /**
     * O(n^2), O(1)
     */
    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            long sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * O(n), O(n)
     * Use Map for previous sum memorization
     */
    public static int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        final HashMap<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);

        int count = 0;
        long sum = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }
        return count;
    }
}
