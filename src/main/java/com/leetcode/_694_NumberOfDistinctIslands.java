package com.leetcode;

/*
https://leetcode.com/problems/number-of-distinct-islands/
Medium. Hash Table, Depth-First Search, Breadth-First Search, Union Find, Hash Function.

You are given an m x n binary matrix grid. An island is a group of 1's
(representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island
can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.

Example 1:
 Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 Output: 1

Example 2:
 Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 Output: 3

Constraints:
 m == grid.length
 n == grid[i].length
 1 <= m, n <= 50
 grid[i][j] is either 0 or 1.
*/

import java.util.HashSet;
import java.util.Set;

/**
 * @see _200_NumberOfIslands
 * @see _695_MaxAreaOfIsland
 * @see _827_MakingALargeIsland
 * @see _1020_NumberOfEnclaves
 * @see _1254_NumberOfClosedIslands
 * @see _1905_CountSubIslands
 */
class _694_NumberOfDistinctIslands {

    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid.length > 50
                || grid[0].length == 0 || grid[0].length > 50) return 0;

        final int R = grid.length, C = grid[0].length;

        final Set<String> islands = new HashSet<>();
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    dfs(grid, sb, r, c, '+');
                    islands.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        return islands.size();
    }

    private static void dfs(int[][] grid, StringBuilder sb, int r, int c, char dir) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) return;

        grid[r][c] = 0;
        sb.append(dir);
        dfs(grid, sb, r + 1, c, 'D');
        dfs(grid, sb, r - 1, c, 'U');
        dfs(grid, sb, r, c + 1, 'R');
        dfs(grid, sb, r, c - 1, 'L');
        sb.append('-');
    }
}
