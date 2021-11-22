package com.leetcode;

/*
https://leetcode.com/problems/longest-increasing-subsequence/
Medium. Array, Binary Search, Dynamic Programming.

Given an integer array nums, return the length of the longest strictly
increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting
some or no elements without changing the order of the remaining elements.
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101],
therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
 1 <= nums.length <= 2500
 -10^4 <= nums[i] <= 10^4

Follow up: Can you come up with an algorithm
that runs in O(n log(n)) time complexity?
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _300_LongestIncreasingSubsequence {

    /**
     * O(n^2), O(n)
     */
    public static int lengthOfLisTopDown(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        // dp[i] = lenOfLis(ending with nums[i])
        final int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        int res = 0;
        for (int n : dp) {
            res = Math.max(res, n);
        }
        return res;
    }

    /**
     * O(n^2), O(n)
     */
    public static int lengthOfLisBottomUp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        final List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                // replace the first element in sub that >= num
                int j = 0;
                while (j < list.size() && num > list.get(j)) {
                    j += 1;
                }
                list.set(j, num);
            }
        }

        // list might not equal to the exact sequence, but with the same size
        return list.size();
    }

    /**
     * Improvement of bottom up DP solution using binary search
     * O(nlogn), O(n)
     */
    public static int lengthOfLis(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        final List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                // replace the first element in sub that >= num
                int j = binarySearch(list, num);
                list.set(j, num);
            }
        }

        // list might not equal to the exact sequence, but with the same size
        return list.size();
    }

    /**
     * Returns the index of the first value >= key
     */
    private static int binarySearch(List<Integer> list, int key) {
        int lo = 0, hi = list.size() - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int val = list.get(mid);
            if (val == key) {
                return mid;
            } else if (val < key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * Patience sorting
     * O(nlogn), O(n)
     */
    public static int lengthOfLisPatience(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        final List<Integer> piles = new ArrayList<>();
        for (int poker : nums) {
            // binary search the index of first value that > key
            int lo = 0, hi = piles.size();
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (piles.get(mid) >= poker) {
                    hi = mid;
                } else /* if (piles.get(mid) < poker) */ {
                    lo = mid + 1;
                }
            }

            if (lo >= piles.size()) {
                piles.add(poker);
            } else {
                piles.set(lo, poker);
            }
        }
        return piles.size();
    }
}
