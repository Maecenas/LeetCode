package com.leetcode;

/*
https://leetcode.com/problems/island-perimeter/
Easy. Hash Table, Math.

You are given a map in form of a two-dimensional integer grid
where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally).
 The grid is completely surrounded by water, and there is exactly one island
 (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water
around the island). One cell is a square with side length 1.
The grid is rectangular, width and height don't exceed 100.
Determine the perimeter of the island.

Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:
*/

class _463_IslandPerimeter {

    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        final int R = grid.length, C = grid[0].length;
        int islands = 0, neighbours = 0;

        //for (int i = 0; i < R; i++) {
        //    for (int j = 0; j < C; j++) {
        //        if (grid[i][j] == 1) {
        //            islands++;  // count islands
        //            if (i < R - 1 && grid[i + 1][j] == 1) neighbours++;  // count down neighbours
        //            if (j < C - 1 && grid[i][j + 1] == 1) neighbours++;  // count right neighbours
        //        }
        //    }
        //}
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    islands++;  // count islands
                    if (i > 0 && grid[i - 1][j] == 1) neighbours++;  // count up neighbors
                    if (j > 0 && grid[i][j - 1] == 1) neighbours++;  // count left neighbors
                }
            }
        }
        //return islands * 4 - neighbours * 2;
        return (islands << 2) - (neighbours << 1);
    }
}
