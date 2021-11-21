package com.leetcode;

/*
https://leetcode.com/problems/unique-paths/
Medium. Array, Dynamic Programming.

A robot is located at the top-left corner of a m x n grid
(marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid
(marked 'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:

Input: m = 7, n = 3
Output: 28
*/

import java.util.Arrays;

class _62_UniquePaths {

    /**
     * O(m * n), O(m * n)
     */
    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        else if (m == 1 || n == 1) return 1;

        final int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * O(m * n), O(min(m, n))
     */
    public static int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        else if (m == 1 || n == 1) return 1;

        // make sure m is smaller
        if (m > n) {
            int tmp = m;
            m = n;
            n = tmp;
        }

        final int[] arr = new int[m];
        Arrays.fill(arr, 1);

        // loop through every entry in the list
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                arr[i] += arr[i - 1];
            }
        }

        return arr[m - 1];
    }

    /**
     * O(m * n), O(1)
     * Math Formula Analysis
     */
    public static int uniquePaths3(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        else if (m == 1 || n == 1) return 1;

        // Calculate C(m + n - 2, m - 1) == C(m + n - 2, n - 1)
        int res = 1;
        // Proof: there is no rounding happening
        // consider we iterate i from 1 to k, say i = j,
        // rewrite the final answer as C(n, k), then
        // res = (n - k + j) ! / ((n - k)! * j!) = C(n - k + j, j),
        // obviously C(n - k + j, j) is an integer
        for (int i = 1; i <= Math.min(m - 1, n - 1); i++) {
            // res *= Math.ceil((m + n - 1 - i) / i)
            res = res * (m + n - 1 - i) / i;
        }
        return res;
    }
}
