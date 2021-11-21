package com.leetcode;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
Medium. Array, Two Pointers.

Given a sorted array nums, remove the duplicates in-place such that
duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this
by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums
being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.

Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums
being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.

Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means
modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
*/

/**
 * @see _26_RemoveDuplicatesFromSortedArray
 */
class _80_RemoveDuplicatesFromSortedArrayII {

    public static int removeDuplicates(int[] nums) {
        //return removeDuplicates(nums, 2);
        if (nums == null) return 0;

        final int len = nums.length;
        if (len <= 2) return len;

        int slow = 0;
        for (int fast = 0; fast < len; fast++) {
            if (slow < 2 || nums[fast] != nums[slow - 2]) {
                if (fast != slow) nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /**
     * Follow up: A general solution to such problems
     */
    public static int removeDuplicates(int[] nums, int k) {
        if (nums == null) return 0;

        final int len = nums.length;
        if (len <= k) return len;

        int slow = 0;
        //for (int num : nums) {
        //    if (slow < k || num != nums[slow - k]) {
        //        nums[slow] = num;
        //        slow++;
        //    }
        //}
        for (int fast = 0; fast < len; fast++) {
            if (slow < k || nums[fast] != nums[slow - k]) {
                if (fast != slow) nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
