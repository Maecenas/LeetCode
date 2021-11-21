package com.leetcode;

/*
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
Hard. Array, Binary Search.

Suppose an array sorted in ascending order is rotated at some pivot
unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1

Example 2:

Input: [2,2,2,0,1]
Output: 0

Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
*/

/**
 * @see _153_FindMinimumInRotatedSortedArray
 * @see _33_SearchInRotatedSortedArray
 * @see _81_SearchInRotatedSortedArrayII
 */
class _154_FindMinimumInRotatedSortedArrayII {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        else if (nums.length == 1) return nums[0];

        int lo = 0, hi = nums.length - 1, mid;

        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);

            if (nums[lo] == nums[mid] && nums[mid] == nums[hi]) {
                // Switch to O(n) sequential search
                for (int i = lo; i < hi; i++) {
                    if (nums[i] > nums[i + 1]) {
                        return nums[i + 1];
                    }
                }
                return nums[lo];
            } else if (nums[mid] <= nums[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo];
    }
}
