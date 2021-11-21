package com.leetcode;

/*
https://leetcode.com/problems/minimum-path-sum/
Medium. Array, Dynamic Programming.

Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right
which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

class _64_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return Integer.MIN_VALUE;

        final int m = grid.length, n = grid[0].length;

        if (m < n) {
            int[] arr = new int[m];
            arr[0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                arr[i] = arr[i - 1] + grid[i][0];
            }

            for (int j = 1; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    arr[i] = grid[i][j]
                            + ((i == 0)
                            ? arr[i]
                            : Math.min(arr[i], arr[i - 1]));
                }
            }
            return arr[m - 1];
        } else {
            // n <= m
            int[] arr = new int[n];
            arr[0] = grid[0][0];
            for (int j = 1; j < n; j++) {
                arr[j] = arr[j - 1] + grid[0][j];
            }

            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[j] = grid[i][j]
                            + ((j == 0)
                            ? arr[j]
                            : Math.min(arr[j], arr[j - 1]));
                }
            }
            return arr[n - 1];
        }
    }
}
