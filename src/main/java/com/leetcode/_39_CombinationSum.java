package com.leetcode;

/*
https://leetcode.com/problems/combination-sum/
Medium. Array, Backtracking.

Given a set of candidate numbers (candidates) (without duplicates)
and a target number (target), find all unique combinations in candidates
where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see _40_CombinationSumII
 * @see _216_CombinationSumIII
 * @see _377_CombinationSumIV
 */
class _39_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            while (start < nums.length) {
                arr.add(nums[start]);
                backtrack(res, arr, nums, target - nums[start], start); // not i + 1 because we can reuse same elements
                arr.remove(arr.size() - 1);
                start++;
            }
        }
    }
}
