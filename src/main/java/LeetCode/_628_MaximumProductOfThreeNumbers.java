package LeetCode;

/*
https://leetcode.com/problems/maximum-product-of-three-numbers/
Easy. Array, Math.

Given an integer array, find three numbers whose product is maximum
and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6

Example 2:

Input: [1,2,3,4]
Output: 24

Note:

The length of the given array will be in range [3,104]
and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input
won't exceed the range of 32-bit signed integer.
*/

/**
 * @see _152_MaximumProductSubarray
 */
class _628_MaximumProductOfThreeNumbers {

    public static int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) return Integer.MIN_VALUE;

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
