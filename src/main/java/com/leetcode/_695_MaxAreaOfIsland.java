package com.leetcode;

/*
https://leetcode.com/problems/max-area-of-island/
Medium. Array, Depth-first Search.

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
(representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

 Find the maximum area of an island in the given 2D array.
(If there is no island, the maximum area is 0.)

Example 1:

[
  [0,0,1,0,0,0,0,1,0,0,0,0,0],
  [0,0,0,0,0,0,0,1,1,1,0,0,0],
  [0,1,1,0,1,0,0,0,0,0,0,0,0],
  [0,1,0,0,1,1,0,0,1,0,1,0,0],
  [0,1,0,0,1,1,0,0,1,1,1,0,0],
  [0,0,0,0,0,0,0,0,0,0,1,0,0],
  [0,0,0,0,0,0,0,1,1,1,0,0,0],
  [0,0,0,0,0,0,0,1,1,0,0,0,0]
]

Given the above grid, return 6. Note the answer is not 11,
because the island must be connected 4-directionally.

Example 2:

[
  [0,0,0,0,0,0,0,0]
]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.
*/

/**
 * @see _200_NumberOfIslands
 * @see _694_NumberOfDistinctIslands
 * @see _1020_NumberOfEnclaves
 * @see _1254_NumberOfClosedIslands
 * @see _1905_CountSubIslands
 */
class _695_MaxAreaOfIsland {

    /**
     * O(m * n), O(1)
     * Recursive
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        final int R = grid.length, C = grid[0].length;

        int res = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                res = Math.max(res, dfs(grid, r, c));
            }
        }
        return res;
    }

    private static int dfs(final int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) return 0;

        grid[r][c] = 0;
        return dfs(grid, r + 1, c)
                + dfs(grid, r - 1, c)
                + dfs(grid, r, c + 1)
                + dfs(grid, r, c - 1)
                + 1;
    }
}
