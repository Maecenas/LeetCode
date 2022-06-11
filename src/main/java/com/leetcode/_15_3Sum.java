package com.leetcode;

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

/**
 * @see _1_TwoSum
 * @see _16_3SumClosest
 * @see _18_4Sum
 * @see _259_ThreeSumSmaller
 */
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
                target = -nums[i];

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

    public static List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();

        Arrays.sort(nums);
        return threeSumTarget(nums, 0, 0);
    }

    static List<List<Integer>> threeSumTarget(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();

        final int n = nums.length;
        for (int i = start; i < n; i++) {
            List<List<Integer>> tuples = twoSum(nums, target - nums[i], i + 1);
            for (List<Integer> tuple : tuples) {
                res.add(Arrays.asList(nums[i], tuple.get(0), tuple.get(1)));
            }
            while (i < n - 1 && nums[i + 1] == nums[i]) i++;
        }

        return res;
    }

    /**
     * Returns the list of tuples that add up to target
     *
     * @param nums the sorted array of integer
     * @param target the target to search
     * @param start search range is nums[start:]
     * @return list of tuples that add up to target
     */
    private static List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();

        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int left = nums[lo], right = nums[hi];
            int sum = nums[lo] + nums[hi];
            if (sum < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right) hi--;
            } else /* if (sum == target) */ {
                res.add(Arrays.asList(left, right));
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
        return res;
    }
}
