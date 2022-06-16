package com.leetcode;

/*
https://leetcode.com/problems/number-of-enclaves/
Medium. Array, Depth-First Search, Breadth-First Search, Union Find, Matrix.

You are given an m x n binary matrix grid,
where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent
(4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the
boundary of the grid in any number of moves.

Example 1:
 Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 Output: 3
 Explanation: There are three 1s that are enclosed by 0s, and one 1 that is
  not enclosed because its on the boundary.

Example 2:
 Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 Output: 0
 Explanation: All 1s are either on the boundary or can reach the boundary.

Constraints:
 m == grid.length
 n == grid[i].length
 1 <= m, n <= 500
 grid[i][j] is either 0 or 1.
*/

/**
 * @see _200_NumberOfIslands
 * @see _694_NumberOfDistinctIslands
 * @see _695_MaxAreaOfIsland
 * @see _827_MakingALargeIsland
 * @see _1254_NumberOfClosedIslands
 * @see _1905_CountSubIslands
 */
class _1020_NumberOfEnclaves {

    public static int numEnclaves(int[][] grid) {
        if (grid == null) return 0;

        final int R = grid.length, C = grid[0].length;
        for (int r = 0; r < R; r++) {
            dfs(grid, r, 0);
            dfs(grid, r, C - 1);
        }
        for (int c = 0; c < C; c++) {
            dfs(grid, 0, c);
            dfs(grid, R - 1, c);
        }

        int res = 0;
        for (int r = 1; r < R - 1; r++) {
            for (int c = 1; c < C - 1; c++) {
                if (grid[r][c] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private static void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) return;

        grid[r][c] = 0;
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
