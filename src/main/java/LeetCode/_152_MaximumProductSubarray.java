package LeetCode;

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
 */
class _152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if (nums == null) return 0;
        else if (nums.length == 0) return nums[0];

        int res = Integer.MIN_VALUE, max = 1, min = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            // decouple max and min
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
