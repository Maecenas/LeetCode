package com.leetcode;

/*
https://leetcode.com/problems/spiral-matrix-ii/
Medium. Array.

Given a positive integer n, generate a square matrix
filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/**
 * @see _54_SpiralMatrix
 * @see _885_SpiralMatrixIII
 */
class _59_SpiralMatrixII {

    public static int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][];

        final int[][] res = new int[n][n];

        int R = n - 1, C = n - 1, r = 0, c = 0;
        int count = 1;

        while (r <= R && c <= C) {
            // (r, 0) -> (r, C)
            for (int w = c; w <= C; w++) {
                res[r][w] = count;
                count++;
            }
            // (r + 1, C) -> (R, C)
            for (int h = r + 1; h <= R; h++) {
                res[h][C] = count;
                count++;
            }
            // (R, C - 1) -> (R, c)
            if (r != R) {
                for (int w = C - 1; w >= c; w--) {
                    res[R][w] = count;
                    count++;
                }
            }
            // (R - 1, c) -> (r + 1, c)
            if (c != C) {
                for (int h = R - 1; h > r; h--) {
                    res[h][c] = count;
                    count++;
                }
            }
            r++;
            R--;
            c++;
            C--;
        }
        return res;
    }
}
