package LeetCode;

/*
https://leetcode.com/problems/array-partition-i/
Easy. Array, Math.

Given an array of 2n integers, your task is to group these integers
into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn)
which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:

Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

Note:

n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
*/

import java.util.Arrays;

class _561_ArrayPartitionI {

    private static final int RANGE = 10000;  // range of positive input

    /**
     * To get the maximum sum, we have to minimize distance in each pair
     * by choosing the smaller number in a sorted array
     * res = (sum::nums - sum(dist(a, b))) / 2
     */
    public static int arrayPairSum(int[] nums) {
        if (nums == null || nums.length < 2) return Integer.MIN_VALUE;

        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    /**
     * Bucket Sort, barely used as it is confusing
     */
    public static int arrayPairSum2(int[] nums) {
        if (nums == null || nums.length < 2) return Integer.MIN_VALUE;

        final int[] map = new int[2 * RANGE + 1];
        for (int num : nums) {
            map[num + RANGE]++;
        }

        int res = 0, odd = 0;
        for (int i = -RANGE; i <= RANGE; i++) {
            int count = map[i + RANGE];
            if (count != 0) {
                res += ((count - odd + 1) >> 1) * i;
                odd = (count - odd) & 0x1;
            }
        }
        return res;
    }
}
