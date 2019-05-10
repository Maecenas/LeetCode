package LeetCode;

/*
https://leetcode.com/problems/search-insert-position/
Easy. Array, Binary Search.

Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2

Example 2:

Input: [1,3,5,6], 2
Output: 1

Example 3:

Input: [1,3,5,6], 7
Output: 4

Example 4:

Input: [1,3,5,6], 0
Output: 0
*/

class _35_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int lo = 0, hi = nums.length - 1, mid;
        while (lo < hi) {
            mid = lo + (hi - lo) >> 1;
            if (target > nums[mid]) lo = mid + 1;
            else if (target < nums[mid]) hi = mid - 1;
            else return mid;
        }
        return lo;
    }
}
