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

class _695_MaxAreaOfIsland {

    /**
     * O(m * n), O(m * n)
     * Recursive
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        final int R = grid.length, C = grid[0].length;

        final boolean[][] marked = new boolean[R][C];

        int res = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                res = Math.max(res, getArea(grid, marked, r, c));
            }
        }
        return res;
    }

    private static int getArea(final int[][] grid, final boolean[][] marked, int r, int c) {
        if (marked[r][c] || grid[r][c] == 0) return 0;

        marked[r][c] = true;
        return (1
                + (r < grid.length - 1 ? getArea(grid, marked, r + 1, c) : 0)
                + (r >= 1 ? getArea(grid, marked, r - 1, c) : 0)
                + (c < grid[0].length - 1 ? getArea(grid, marked, r, c + 1) : 0)
                + (c >= 1 ? getArea(grid, marked, r, c - 1) : 0)
        );
    }
}
