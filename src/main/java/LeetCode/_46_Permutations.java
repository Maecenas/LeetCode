package LeetCode;

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

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
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
}
