package LeetCode;

/*
https://leetcode.com/problems/single-number/
Easy. Hash Table, Bit Manipulation.

Given a non-empty array of integers, every
element appears twice except for one.
Find that single one.

Note:

Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1

Example 2:

Input: [4,1,2,1,2]
Output: 4
*/

/**
 * @see _41_FirstMissingPositive
 * @see _137_SingleNumberII
 * @see _260_SingleNumberIII
 * @see _268_MissingNumber
 * @see _287_FindTheDuplicateNumber
 * @see _645_SetMismatch
 */
class _136_SingleNumber {

    public static int singleNumber(int[] nums) {
        if (nums == null) throw new IllegalArgumentException();

        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
