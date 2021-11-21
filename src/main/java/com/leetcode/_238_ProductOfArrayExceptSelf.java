package com.leetcode;

/*
https://leetcode.com/problems/product-of-array-except-self/
Medium. Array.

Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to
the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
(The output array does not count as extra space
for the purpose of space complexity analysis.)
*/

/**
 * @see _42_TrappingRainWater
 * @see _152_MaximumProductSubarray
 */
class _238_ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        final int len = nums.length;
        final int[] res = new int[len];

        // Calculate B[i] by two parts
        // B[i] = {A[0] * A[1] * ... A[i - 1]} * {A[i + 1] * A[i + 2] * ... A[n]}

        //for (int i = 0, product = 1; i < len; product *= nums[i], i++) {
        int i = 0, product = 1;
        while (i < len) {
            res[i] = product;
            product *= nums[i];
            i++;
        }

        //for (int i = len - 1, product = 1; i >= 0; product *= nums[i], i--) {
        i = len - 1;
        product = 1;
        while (i >= 0) {
            res[i] *= product;
            product *= nums[i];
            i--;
        }
        return res;
    }
}
