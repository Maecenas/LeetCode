package com.leetcode;

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

import static com.leetcode._15_3Sum.threeSumTarget;

/**
 * @see _1_TwoSum
 * @see _15_3Sum
 */
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

    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();

        Arrays.sort(nums);
        return fourSumTarget(nums, target, 0);
    }

    private static List<List<Integer>> fourSumTarget(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();

        final int n = nums.length;
        for (int i = start; i < n; i++) {
            List<List<Integer>> tuples = threeSumTarget(nums, target - nums[i], i + 1);
            for (List<Integer> tuple : tuples) {
                res.add(Arrays.asList(nums[i], tuple.get(0), tuple.get(1)));
            }
            while (i < n - 1 && nums[i + 1] == nums[i]) i++;
        }

        return res;
    }

    public static List<List<Integer>> fourSum3(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();

        Arrays.sort(nums);
        return nSumTarget(4, nums, target, 0);
    }

    private static List<List<Integer>> nSumTarget(int n, int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        final int size = nums.length;
        if (n < 2 || size < n) return res;
        if (n == 2) {
            int lo = start, hi = size - 1;
            while (lo < hi) {
                int left = nums[lo], right = nums[hi];
                int sum = nums[lo] + nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) hi--;
                } else /* if (sum == target) */ {
                    List<Integer> tuple = new ArrayList<>();
                    tuple.add(left);
                    tuple.add(right);
                    res.add(tuple);

                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else /* if (n > 2) */ {
            for (int i = start; i < size; i++) {
                List<List<Integer>> sub = nSumTarget(n - 1, nums, target - nums[i], i + 1);
                for (List<Integer> arr : sub) {
                    arr.add(0, nums[i]);
                    res.add(arr);
                }
                while (i < size - 1 && nums[i + 1] == nums[i]) i++;
            }
        }
        return res;
    }
}
