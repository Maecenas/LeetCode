package com.leetcode;

/*
https://leetcode.com/problems/count-sub-islands/
Medium. Array, Depth-First Search, Breadth-First Search, Union Find, Matrix.

You are given two m x n binary matrices grid1 and grid2 containing only 0's
(representing water) and 1's (representing land). An island is a group of 1's
connected 4-directionally (horizontal or vertical).
Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1
that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

Example 1:

Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]],
grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid
on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island.
There are three sub-islands.

Example 2:

Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]],
grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2
Explanation: In the picture above, the grid on the left is grid1 and the grid
on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island.
There are two sub-islands.

Constraints:
 m == grid1.length == grid2.length
 n == grid1[i].length == grid2[i].length
 1 <= m, n <= 500
 grid1[i][j] and grid2[i][j] are either 0 or 1.
*/


/**
 * @see _200_NumberOfIslands
 * @see _694_NumberOfDistinctIslands
 * @see _695_MaxAreaOfIsland
 * @see _1020_NumberOfEnclaves
 * @see _1254_NumberOfClosedIslands
 */
class _1905_CountSubIslands {

    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid2 == null
                || grid1.length != grid2.length
                || grid1.length == 0 || grid1.length > 500
                || grid1[0].length != grid2[0].length
                || grid1[0].length == 0 || grid1[0].length > 500) return 0;

        final int R = grid1.length, C = grid1[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid1[r][c] == 0 && grid2[r][c] == 1) {
                    // flood grid2[r][c]
                    dfs(grid2, r, c);
                }
            }
        }

        // count number of islands in grid2
        int res = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid2[r][c] == 1) {
                    res++;
                    dfs(grid2, r, c);
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
