package com.leetcode;

/*
https://leetcode.com/problems/subsets/
Medium. Array, Backtracking, Bit Manipulation.

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _90_SubsetsII
 */
class _78_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>(1 << nums.length);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int[] nums, int start) {
        res.add(new ArrayList<>(arr));
        for (int i = start; i < nums.length; i++) {
            arr.add(nums[i]);
            backtrack(res, arr, nums, i + 1);
            arr.remove(arr.size() - 1);
        }
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>(1 << nums.length);
        res.add(new ArrayList<>());
        for (int num : nums) {
            backtrack(res, num);
        }
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, int val) {
        for (int i = res.size() - 1; i >= 0; i--) {
            final List<Integer> arr = new ArrayList<>(res.get(i));
            arr.add(val);
            res.add(arr);
        }
    }
}
