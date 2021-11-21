package com.leetcode;

/*
https://leetcode.com/problems/majority-element-ii/
Medium. Array.

Given an integer array of size n, find all elements
that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]

Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _169_MajorityElement
 */
class _229_MajorityElementII {

    public static List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<Integer> res = new ArrayList<>();
        final int len = nums.length;

        int count1 = 0, candidate1 = 0, count2 = 0, candidate2 = 0;

        // first round to find candidates
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1++;
            } else if (count2 == 0) {
                candidate2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        // second round to confirm
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            res.add(candidate1);
        }
        if (count2 > len / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
