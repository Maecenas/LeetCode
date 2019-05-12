package LeetCode;

/*
https://leetcode.com/problems/3sum/
Medium. Array, Two Pointers.

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _15_3Sum {

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int lo, hi, target;

        for (int i = 0; i < nums.length - 2; i++) {
            // early return
            if (nums[i] > 0) break;
            // only search for the first occurrence in duplicates
            if (i == 0 || nums[i] != nums[i - 1]) {
                lo = i + 1;
                hi = nums.length - 1;
                target = 0 - nums[i];

                while (lo < hi) {
                    if (nums[lo] + nums[hi] < target) {
                        lo++;
                    } else if (nums[lo] + nums[hi] > target) {
                        hi--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // skip duplicates
                        // also ensure index in bound by checking (lo < hi)
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
