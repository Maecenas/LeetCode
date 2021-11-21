package com.leetcode;

/*
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
Medium. Array, Binary Search.

Suppose an array sorted in ascending order is rotated at some pivot
unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1

Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/

/**
 * @see _154_FindMinimumInRotatedSortedArrayII
 * @see _33_SearchInRotatedSortedArray
 * @see _81_SearchInRotatedSortedArrayII
 */
class _153_FindMinimumInRotatedSortedArray {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        else if (nums.length == 1) return nums[0];

        int lo = 0, hi = nums.length - 1, mid;
        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo];
    }
}
