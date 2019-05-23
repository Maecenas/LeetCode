package LeetCode;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
Medium. Array, Binary Search.

Suppose an array sorted in ascending order is rotated at some pivot
unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search.
If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Follow up:

This is a follow up problem to Search in Rotated Sorted Array,
where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
*/

/**
 * @see _153_FindMinimumInRotatedSortedArray
 * @see _154_FindMinimumInRotatedSortedArrayII
 * @see _33_SearchInRotatedSortedArray
 */
class _81_SearchInRotatedSortedArrayII {

    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int lo = 0, hi = nums.length - 1, mid;

        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);

            if (nums[mid] == target) return true;

            if (nums[lo] == nums[mid] && nums[mid] == nums[hi]) {
                // Switch to O(n) sequential search
                for (int i = lo; i <= hi; i++) {
                    if (nums[i] == target) return true;
                }
                return false;
            } else if (nums[lo] > nums[mid] || nums[mid] < nums[hi]) {
                // the right side is sorted, or the left side is unsorted
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                // the left side is sorted, or the right side is unsorted
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return false;
    }
}
