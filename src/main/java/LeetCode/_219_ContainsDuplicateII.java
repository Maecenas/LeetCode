package LeetCode;

/*
https://leetcode.com/problems/contains-duplicate-ii/
Easy. Array, Hash Table.

Given an array of integers and an integer k, find out whether
there are two distinct indices i and j in the array such that
nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

import java.util.HashSet;

class _219_ContainsDuplicateII {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k <= 0) return false;

        final HashSet<Integer> set = new HashSet<>(k);
        for (int i = 0; i < nums.length; i++) {
            // sliding window of size k
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
