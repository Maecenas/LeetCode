package com.leetcode;

/*
https://leetcode.com/problems/number-of-closed-islands/
Medium. Array, Depth-First Search, Breadth-First Search, Union Find, Matrix.

Given a 2D grid consists of 0s (land) and 1s (water). An island is a maximal
4-directionally connected group of 0s and a closed island is an island totally
 (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.
Example 1:
  Input: grid = [[1,1,1,1,1,1,1,0],
                 [1,0,0,0,0,1,1,0],
                 [1,0,1,0,1,1,1,0],
                 [1,0,0,0,0,1,0,1],
                 [1,1,1,1,1,1,1,0]]
  Output: 2
  Explanation:
    Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
  Input: grid = [[0,0,1,0,0],
                 [0,1,0,1,0],
                 [0,1,1,1,0]]
  Output: 1

Example 3:
  Input: grid = [[1,1,1,1,1,1,1],
                 [1,0,0,0,0,0,1],
                 [1,0,1,1,1,0,1],
                 [1,0,1,0,1,0,1],
                 [1,0,1,1,1,0,1],
                 [1,0,0,0,0,0,1],
                 [1,1,1,1,1,1,1]]
  Output: 2

Constraints:
 1 <= grid.length, grid[0].length <= 100
 0 <= grid[i][j] <= 1
*/

/**
 * @see _200_NumberOfIslands
 * @see _694_NumberOfDistinctIslands
 * @see _695_MaxAreaOfIsland
 * @see _827_MakingALargeIsland
 * @see _1020_NumberOfEnclaves
 * @see _1905_CountSubIslands
 */
class _1254_NumberOfClosedIslands {

    public static int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid.length > 100
                || grid[0].length == 0 || grid[0].length > 100) return 0;

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
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 0) {
                    res++;
                    dfs(grid, r, c);
                }
            }
        }
        return res;
    }

    private static void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) return;

        grid[r][c] = 1;
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
