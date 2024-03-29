package com.leetcode;

/*
https://leetcode.com/problems/combination-sum-ii/
Medium. Array, Backtracking.

Given a collection of candidate numbers (candidates)
and a target number (target), find all unique combinations in candidates
where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see _39_CombinationSum
 * @see _216_CombinationSumIII
 * @see _377_CombinationSumIV
 */
class _40_CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();

        Arrays.sort(candidates);
        final List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int[] nums, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(arr));
        } else if (target > 0) {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;  // skip duplicates
                arr.add(nums[i]);
                backtrack(res, arr, nums, target - nums[i], i + 1);
                arr.remove(arr.size() - 1);
            }
        }
    }
}
