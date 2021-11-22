package com.leetcode;

/*
https://leetcode.com/problems/permutations/
Medium. Backtracking.

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class _46_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<List<Integer>> curr = new ArrayList<>();
        List<List<Integer>> prev;
        //res.add(Arrays.asList(nums[0]));
        curr.add(Collections.singletonList(nums[0]));

        for (int i = 1; i < nums.length; i++) {
            prev = new ArrayList<>(curr);
            curr.clear();
            for (int j = 0; j <= i; j++) {
                for (List<Integer> l : prev) {
                    List<Integer> arr = new ArrayList<>(l);
                    arr.add(j, nums[i]);
                    curr.add(arr);
                }
            }
        }
        return curr;
    }

    public static List<List<Integer>> permute2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        final List<Integer> track = new ArrayList<>(nums.length);
        backtrack(nums, track, res);
        return res;
    }

    private static void backtrack(int[] nums, List<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int num : nums) {
            if (track.contains(num)) continue;

            track.add(num);
            backtrack(nums, track, res);
            track.remove(track.size() - 1);
        }
    }
}
