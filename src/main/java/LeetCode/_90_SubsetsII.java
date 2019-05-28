package LeetCode;

/*
https://leetcode.com/problems/subsets-ii/
Medium. Array, Backtracking.

Given a collection of integers that might contain duplicates, nums,
return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see _78_Subsets
 */
class _90_SubsetsII {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);
        final List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int[] nums, int start) {
        res.add(new ArrayList<>(arr));
        for (int i = start; i < nums.length; i++) {
            // skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;
            arr.add(nums[i]);
            backtrack(res, arr, nums, i + 1);
            arr.remove(arr.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);
        final List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) start = 0;
            final int size = res.size();
            while (start < size) {
                final List<Integer> arr = new ArrayList<>(res.get(start));
                arr.add(nums[i]);
                res.add(arr);
                start++;
            }
        }
        return res;
    }
}
