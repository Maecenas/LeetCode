package com.leetcode;

/*
https://leetcode.com/problems/unique-paths-ii/
Medium. Array, Dynamic Programming.

A robot is located at the top-left corner of a m x n grid
(marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid
(marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids.
How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

class _63_UniquePathsII {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        else if (obstacleGrid[0][0] == 1) return 0;

        final int m = obstacleGrid.length, n = obstacleGrid[0].length;

        obstacleGrid[0][0] = 1;

        for (int i = 1; i < m; i++) {
            obstacleGrid[i][0] = obstacleGrid[i][0] == 0 ? obstacleGrid[i - 1][0] : 0;
        }

        for (int i = 1; i < n; i++) {
            obstacleGrid[0][i] = obstacleGrid[0][i] == 0 ? obstacleGrid[0][i - 1] : 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        else if (obstacleGrid[0][0] == 1) return 0;

        final int m = obstacleGrid.length, n = obstacleGrid[0].length;
        final int[] arr = new int[m <= n ? m : n];
        // unary logical negation operator ! in C
        //arr[0] = !obstacleGrid[0][0];
        arr[0] = 1 - obstacleGrid[0][0];

        if (arr.length == n) {
            // first row
            for (int j = 1; j < n; j++) {
                arr[j] = obstacleGrid[0][j] == 0 ? arr[j - 1] : 0;
            }

            for (int i = 1; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    arr[0] = 0;
                }
                for (int j = 1; j < n; j++) {
                    arr[j] = obstacleGrid[i][j] == 0 ? arr[j] + arr[j - 1] : 0;
                }
            }
            return arr[n - 1];
        } else {
            // first column
            for (int i = 1; i < m; i++) {
                arr[i] = obstacleGrid[i][0] == 0 ? arr[i - 1] : 0;
            }

            for (int j = 1; j < n; j++) {
                if (obstacleGrid[0][j] == 1) arr[0] = 0;
                for (int i = 1; i < m; i++) {
                    arr[i] = obstacleGrid[i][j] == 0 ? arr[i] + arr[i - 1] : 0;
                }
            }
            return arr[m - 1];
        }
    }
}
