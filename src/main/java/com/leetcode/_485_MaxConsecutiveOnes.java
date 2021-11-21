package com.leetcode;

/*
https://leetcode.com/problems/max-consecutive-ones/
Math. Array, Dynamic Programming.

Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:

Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
The maximum number of consecutive 1s is 3.

Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
*/

class _485_MaxConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int max = 0, maxSoFar = 0;
        for (int num : nums) {
            //maxSoFar = Math.max(maxSoFar, max = (num == 1 ? max + 1 : 0));
            if (num == 1) {
                max++;
            } else {
                // (num == 0)
                maxSoFar = Math.max(max, maxSoFar);
                max = 0;
            }
        }
        return maxSoFar;
    }
}
