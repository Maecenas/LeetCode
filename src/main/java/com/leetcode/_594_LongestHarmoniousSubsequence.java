package com.leetcode;

/*
https://leetcode.com/problems/longest-harmonious-subsequence/
Easy. Hash Table.

We define a harmonious array is an array where the difference between
its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest
harmonious subsequence among all its possible subsequences.

Example 1:

Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Note:
The length of the input array will not exceed 20,000.
*/

import java.util.HashMap;

class _594_LongestHarmoniousSubsequence {

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        final HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        // start search from smallest key to larger keys
        for (int key : count.keySet()) {
            if (count.containsKey(key + 1)) {
                res = Math.max(res, count.get(key) + count.get(key + 1));
            }
        }
        return res;
    }
}
