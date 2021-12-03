package com.leetcode;

/*
https://leetcode.com/problems/binary-search/
Easy. Array, Binary Search.

Given an array of integers nums which is sorted in ascending order,
and an integer target, write a function to search target in nums.
If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
 Input: nums = [-1,0,3,5,9,12], target = 9
 Output: 4
 Explanation: 9 exists in nums and its index is 4

Example 2:
 Input: nums = [-1,0,3,5,9,12], target = 2
 Output: -1
 Explanation: 2 does not exist in nums so return -1

Constraints:
 1 <= nums.length <= 10^4
 -10^4 < nums[i], target < 10^4
 All the integers in nums are unique.
 nums is sorted in ascending order.
*/

/**
 * @see _34_FindFirstAndLastPositionOfElementInSortedArray
 * @see _702_SearchInASortedArrayOfUnknownSize
 */
class _704_BinarySearch {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums.length > 1e4
                || target <= -1e4 || target >= 1e4) return -1;

        int lo = 0, hi = nums.length - 1, mid;
        while (lo <= hi) {
            mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }
}
