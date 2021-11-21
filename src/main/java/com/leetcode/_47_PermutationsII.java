package com.leetcode;

/*
https://leetcode.com/problems/permutations-ii/
Medium. Backtracking.

Given a collection of numbers that might contain duplicates,
return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _47_PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);
        final List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int[] nums, boolean[] marked) {
        if (arr.size() == nums.length) {
            res.add(new ArrayList<>(arr));
        } else {
            for (int i = 0; i < nums.length; i++) {
                // skip duplicates
                if (marked[i] || (i > 0 && nums[i] != nums[i - 1] && !marked[i - 1])) continue;
                marked[i] = true;
                arr.add(nums[i]);
                backtrack(res, arr, nums, marked);
                arr.remove(arr.size() - 1);
                marked[i] = false;
            }
        }
    }
}
