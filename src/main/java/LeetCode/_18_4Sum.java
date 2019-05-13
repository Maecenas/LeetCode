package LeetCode;

/*
https://leetcode.com/problems/4sum/
Medium. Array, Hash Table, Two Pointers.

Given an array nums of n integers and an integer target, are there elements a, b, c, and d
in nums such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _18_4Sum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);
        int sub, lo, hi;

        for (int i = 0; i < nums.length - 3; i++) {
            // only check first occurrence of duplicate keys
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                sub = target - nums[i] - nums[j];
                lo = j + 1;
                hi = nums.length - 1;
                // break only early return
                if (nums[j + 1] + nums[j + 2] > sub) break;
                while (lo < hi) {
                    if (nums[lo] + nums[hi] < sub) {
                        lo++;
                    } else if (nums[lo] + nums[hi] > sub) {
                        hi--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}
