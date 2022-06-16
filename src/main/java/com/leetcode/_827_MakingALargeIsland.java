package com.leetcode;

/*
https://leetcode.com/problems/making-a-large-island/
Hard. Depth-first Search, Breadth-first Search.

You are given an n x n binary matrix grid. You are allowed to change
at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Example 1:
 Input: grid = [[1,0],[0,1]]
 Output: 3
 Explanation: Change one 0 to 1 and connect two 1s, then we get an island with
  area = 3.

Example 2:
 Input: grid = [[1,1],[1,0]]
 Output: 4
 Explanation: Change the 0 to 1 and make the island bigger, only one island
  with area = 4.

Example 3:
 Input: grid = [[1,1],[1,1]]
 Output: 4
 Explanation: Can't change any 0 to 1, only one island with area = 4.

Constraints:
 n == grid.length
 n == grid[i].length
 1 <= n <= 500
 grid[i][j] is either 0 or 1.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @see _200_NumberOfIslands
 * @see _694_NumberOfDistinctIslands
 * @see _695_MaxAreaOfIsland
 * @see _1020_NumberOfEnclaves
 * @see _1254_NumberOfClosedIslands
 * @see _1905_CountSubIslands
 */
class _827_MakingALargeIsland {

    /**
     * O(n^2), O(n^2)
     * Union Find
     */
    public static int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length != grid.length
                || grid.length > 500) return -1;

        final int n = grid.length;
        // DFS every island and give it an index of island
        int index = 2, res = 0;
        final Map<Integer, Integer> islands = new HashMap<>();
        final Set<int[]> ocean = new HashSet<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int area = dfs(grid, r, c, index);
                    islands.put(index, area);
                    res = Math.max(res, area);
                    index++;
                } else if (grid[r][c] == 0) {
                    ocean.add(new int[]{r, c});
                }
            }
        }

        final Set<Integer> visited = new HashSet<>();
        for (int[] p : ocean) {
            int area = 1;
            for (int[] neighbor : neighbors(grid, p[0], p[1])) {
                index = grid[neighbor[0]][neighbor[1]];
                if (index > 1 && !visited.contains(index)) {
                    visited.add(index);
                    area += islands.get(index);
                }
            }
            res = Math.max(res, area);
            visited.clear();
        }
        return res;
    }

    private static int dfs(int[][] grid, int r, int c, int index) {
        int area = 1;
        grid[r][c] = index;
        for (int[] p : neighbors(grid, r, c)) {
            if (grid[p[0]][p[1]] == 1) {
                area += dfs(grid, p[0], p[1], index);
            }
        }
        return area;
    }

    private static List<int[]> neighbors(int[][] grid, int r, int c) {
        final List<int[]> res = new ArrayList<>();
        if (valid(grid, r - 1, c)) res.add(new int[]{r - 1, c});
        if (valid(grid, r + 1, c)) res.add(new int[]{r + 1, c});
        if (valid(grid, r, c + 1)) res.add(new int[]{r, c + 1});
        if (valid(grid, r, c - 1)) res.add(new int[]{r, c - 1});
        return res;
    }

    private static boolean valid(int[][] grid, int r, int c) {
        return (0 <= r && r < grid.length && 0 <= c && c < grid[0].length);
    }
}
