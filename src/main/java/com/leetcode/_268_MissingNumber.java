package com.leetcode;

/*
https://leetcode.com/problems/missing-number/
Easy. Array, Math, Bit Manipulation.

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2

Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?
*/

import java.util.Arrays;

/**
 * @see _41_FirstMissingPositive
 * @see _136_SingleNumber
 * @see _137_SingleNumberII
 * @see _260_SingleNumberIII
 * @see _268_MissingNumber
 * @see _287_FindTheDuplicateNumber
 * @see _645_SetMismatch
 */
class _268_MissingNumber {

    /**
     * O(n), O(1)
     */
    public static int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    /**
     * O(n), O(1) but faster with bit operation
     */
    public static int missingNumberBitManipulation(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * O(nlogn), O(1)
     * If the array came in sorted order, time complexity is the best O(logn)
     */
    public static int missingNumberBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        Arrays.sort(nums);

        // hi = nums.length (n), not (n - 1)
        int lo = 0, hi = nums.length, mid;
        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (nums[mid] > mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
