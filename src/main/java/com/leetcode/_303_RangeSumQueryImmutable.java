package com.leetcode;

/*
https://leetcode.com/problems/range-sum-query-immutable/
Easy. Array, Design, Prefix Sum.

Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right
inclusive where left <= right.

Implement the NumArray class:

 NumArray(int[] nums) Initializes the object with the integer array nums.
 int sumRange(int left, int right) Returns the sum of the elements of nums
  between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

Example 1:
 Input
  ["NumArray", "sumRange", "sumRange", "sumRange"]
  [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 Output
  [null, 1, -1, -3]
 Explanation
  NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
  numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
  numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
  numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

Constraints:
 1 <= nums.length <= 10^4
 -10^5 <= nums[i] <= 10^5
 0 <= left <= right < nums.length
 At most 10⁴ calls will be made to sumRange.

Your NumArray object will be instantiated and called as such:

 NumArray obj = new NumArray(nums);
 int param_1 = obj.sumRange(left,right);
*/

/**
 * @see _304_RangeSumQuery2dImmutable
 */
class _303_RangeSumQueryImmutable {

    static class NumArray {

        private final int[] prefixSum;

        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length > 1e4) throw new IllegalArgumentException();

            prefixSum = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i] = (i >= 1 ? prefixSum[i - 1] : 0) + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            if (left < 0 || right >= prefixSum.length || left > right) throw new IllegalArgumentException();

            return prefixSum[right + 1] - (left > 0 ? prefixSum[left] : 0);
        }
    }
}
