package com.leetcode;

/*
https://leetcode.com/problems/max-consecutive-ones-ii/
Medium. Array, Dynamic Programming, Sliding.

Given a binary array nums, return the maximum number of consecutive 1's
in the array if you can flip at most one 0.

Example 1:
 Input: nums = [1,0,1,1,0]
 Output: 4
 Explanation: Flip the first zero will get the maximum number of consecutive 1s.
  After flipping, the maximum number of consecutive 1s is 4.

Example 2:
 Input: nums = [1,0,1,1,0,1]
 Output: 4

Constraints:
 1 <= nums.length <= 10^5
 nums[i] is either 0 or 1.

Follow up: What if the input numbers come in one by one as an infinite stream?
In other words, you can't store all numbers coming from the stream as it's
too large to hold in memory. Could you solve it efficiently?
*/

/**
 * @see _485_MaxConsecutiveOnes
 * @see _1004_MaxConsecutiveOnesIII
 */
class _487_MaxConsecutiveOnesII {

    public static int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length > 1e5) return -1;

        // sliding window of nums[i:j) that contains at most 1 zeros
        int i = 0, j = 0, zeros = 0, res = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                zeros++;
            }
            j++;
            while (i < j && zeros > 1) {
                if (nums[i] == 0) zeros--;
                i++;
            }
            res = Math.max(res, j - i);
        }

        return res;
    }
}
