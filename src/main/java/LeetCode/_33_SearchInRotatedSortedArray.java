package LeetCode;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
Medium. Array, Binary Search.

Suppose an array sorted in ascending order is rotated at some pivot
unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search.
If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

/**
 * @see _153_FindMinimumInRotatedSortedArray
 * @see _154_FindMinimumInRotatedSortedArrayII
 * @see _81_SearchInRotatedSortedArrayII
 */
class _33_SearchInRotatedSortedArray {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int lo = 0, hi = nums.length - 1, mid;

        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            if (nums[mid] == target) return mid;

            if (nums[lo] < nums[mid]) {
                // nums is ascending during [lo, mid]
                if (nums[lo] == target) return lo;
                if (nums[lo] < target && nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // nums is ascending during [mid, hi]
                if (nums[hi] == target) return hi;
                if (nums[mid] < target && nums[hi] > target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
