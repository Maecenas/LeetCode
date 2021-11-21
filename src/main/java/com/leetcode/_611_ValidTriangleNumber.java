package com.leetcode;

/*
https://leetcode.com/problems/valid-triangle-number/
Medium. Array.

Given an array consists of non-negative integers, your task is to count
the number of triplets chosen from the array that can make triangles
if we take them as side lengths of a triangle.

Example 1:

Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Note:

The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
*/

import java.util.Arrays;

class _611_ValidTriangleNumber {

    /**
     * O(n^2logn), O(logn)
     */
    public static int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]);
                count += k - j - 1;
            }
        }
        return count;
    }

    /**
     * Return the least index i that nums[i] > target
     */
    private static int binarySearch(final int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * O(n^2), O(logn)
     */
    public static int triangleNumber2(int[] nums) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }
}
