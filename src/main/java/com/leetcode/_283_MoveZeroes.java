package com.leetcode;

/*
https://leetcode.com/problems/move-zeroes/
Easy. Array, Two Pointers.

Given an array nums, write a function to move all 0's to the end of it
while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

class _283_MoveZeroes {

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // In this special case, you don't need a swap() since we know that
        // the leading element (swapped to the end) must be all 0,
        // so there's no need to use swap for backup previous element

        //for (int slow = 0, fast = 0; fast < nums.length; fast++) {
        //    if (nums[fast] != 0) {
        //        swap(nums, slow++, fast);
        //    }
        //}

        int slow = 0;
        for (int num : nums) {
            if (num != 0) nums[slow++] = num;
        }

        // element after index slow should all be 0
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }
}
