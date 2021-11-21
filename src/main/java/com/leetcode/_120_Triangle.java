package com.leetcode;

/*
https://leetcode.com/problems/triangle/
Medium. Array, Dynamic Programming.

Given a triangle, find the minimum path sum from top to bottom.
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space,
where n is the total number of rows in the triangle.
*/

import java.util.List;

class _120_Triangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        final int len = triangle.size();
        final int[] dp = new int[len];

        final List<Integer> lastRow = triangle.get(len - 1);
        for (int i = 0; i < len; i++) {
            dp[i] = lastRow.get(i);
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            final List<Integer> row = triangle.get(i);
            for (int j = 0; j < i + 1; j++) {
                dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
