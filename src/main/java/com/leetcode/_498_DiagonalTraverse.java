package com.leetcode;

/*
https://leetcode.com/problems/diagonal-traverse/
Medium. Array, Matrix, Simulation.

Given an m x n matrix mat, return an array of
all the elements of the array in a diagonal order.

Example 1:
 Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 Output: [1,2,4,7,5,3,6,8,9]

Example 2:
 Input: mat = [[1,2],[3,4]]
 Output: [1,2,3,4]

Constraints:
 m == mat.length
 n == mat[i].length
 1 <= m, n <= 10^4
 1 <= m * n <= 10^4
 -10^5 <= mat[i][j] <= 10^5
*/

class _498_DiagonalTraverse {

    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0 || mat.length > 1e4
            || mat[0] == null || mat[0].length == 0 || mat[0].length > 1e4
            || mat.length * mat[0].length > 1e4) return new int[0];

        final int R = mat.length, C = mat[0].length, len = R * C;
        final int[] res = new int[len];
        int cnt = 0;
        for (int diagonal = 0; diagonal <= R + C - 2; diagonal++) {
            if ((diagonal & 0b1) == 0b1) {
                int r = Math.max(0, diagonal - C + 1), c = diagonal - r;
                while (r < R && c >= 0) {
                    res[cnt++] = mat[r][c];
                    r++;
                    c--;
                }
            } else {
                int c = Math.max(0, diagonal - R + 1), r = diagonal - c;
                while (c < C && r >= 0) {
                    res[cnt++] = mat[r][c];
                    c++;
                    r--;
                }
            }
        }

        return res;
    }
}
