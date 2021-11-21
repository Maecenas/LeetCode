package com.leetcode;

/*
https://leetcode.com/problems/shortest-path-in-binary-matrix/
Medium.

In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if
it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally
(ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.
If such a path does not exist, return -1.

Example 1:

Input: [[0,1],[1,0]]
Output: 2
Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
*/

import java.util.ArrayDeque;
import java.util.Deque;

class _1091_ShortestPathInBinaryMatrix {

    private static final int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};

    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || grid.length != grid[0].length) return 0;

        final int n = grid.length;
        // early return
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;

        final Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});

        int res = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] curr = q.poll();
                // assert curr != null;
                if (curr[0] == n - 1 && curr[1] == n - 1) {
                    // restore(grid);
                    return res + 1;
                }

                for (int k = 0; k < 8; k++) {
                    int x0 = curr[0] + neighbors[k][0];
                    int y0 = curr[1] + neighbors[k][1];

                    if (x0 >= 0 && x0 < n && y0 >= 0 && y0 < n && grid[x0][y0] == 0) {
                        q.add(new int[]{x0, y0});
                        grid[x0][y0] = ~grid[x0][y0];
                    }
                }
            }
            res++;
        }

        // restore(grid);
        return -1;
    }

    /**
     * Restore modified input
     */
    private static void restore(int[][] grid) {
        final int len = grid.length;
        for (int[] row : grid) {
            for (int c = 0; c < len; c++) {
                if (row[c] < 0) {
                    row[c] = ~row[c];
                }
            }
        }
    }
}
