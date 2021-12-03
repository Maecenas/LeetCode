package com.leetcode;

/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Medium. Array, Binary Search.

Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

/**
 * @see _702_SearchInASortedArrayOfUnknownSize
 * @see _704_BinarySearch
 */
class _34_FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        final int left = searchRange(nums, target, true);
        // early return
        if (left == nums.length || nums[left] != target) return new int[]{-1, -1};
        final int right = searchRange(nums, target, false) - 1;

        return new int[]{left, right};
    }

    /**
     * Returns the leftmost (or rightmost - 1) index at which target should be
     * inserted in sorted array by binary search
     */
    private static int searchRange(final int[] nums, final int target, final boolean isLeft) {
        int lo = 0, hi = nums.length, mid;

        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (target < nums[mid] || (isLeft && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}
