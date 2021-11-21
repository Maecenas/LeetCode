package com.leetcode;

/*
https://leetcode.com/problems/maximum-product-subarray/
Medium. Array, Dynamic Programming.

Given an integer array nums, find the contiguous subarray within an array
(containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

/**
 * @see _53_MaximumSubarray
 * @see _198_HouseRobber
 * @see _238_ProductOfArrayExceptSelf
 * @see _628_MaximumProductOfThreeNumbers
 * @see _713_SubarrayProductLessThanK
 */
class _152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return nums[0];

        int res = Integer.MIN_VALUE, max = 1, min = 1;

        for (int num : nums) {
            if (num < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            // decouple max and min
            max = Math.max(num, max * num);
            min = Math.min(num, min * num);
            res = Math.max(res, max);
        }
        return res;
    }
}
