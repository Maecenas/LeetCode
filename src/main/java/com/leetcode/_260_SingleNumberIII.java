package com.leetcode;

/*
https://leetcode.com/problems/single-number-iii/
Medium. Bit Manipulation.

Given an array of numbers nums, in which exactly two elements appear
only once and all the other elements appear exactly twice.
Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]

Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant space complexity?
*/

/**
 * @see _136_SingleNumber
 * @see _137_SingleNumberII
 * @see _268_MissingNumber
 * @see _287_FindTheDuplicateNumber
 * @see _645_SetMismatch
 */
class _260_SingleNumberIII {

    public static int[] singleNumber(int[] nums) {
        if (nums == null || nums.length < 2) return new int[0];

        final int[] res = new int[2];

        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get last the bit of 1
        // -x = ~x + 1
        diff &= -diff;
        // remove one 1 bit from LSB
        //diff &= (diff - 1)

        for (int num : nums) {
            if ((num & diff) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
