package com.leetcode;

/*
https://leetcode.com/problems/flipping-an-image/
Easy. Array.

Given a binary matrix A, we want to flip the image horizontally,
then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.
For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].

To invert an image means that each 0 is replaced by 1, and each 1 is
replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].

Example 1:

Input: [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]

Example 2:

Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

Notes:

1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1
*/

class _832_FlippingAnImage {

    public static int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return new int[0][];

        final int R = A.length, C = A[0].length;
        final int[][] res = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                res[r][c] = A[r][A[0].length - c - 1] ^ 1;
            }
        }
        return res;
    }

    public static int[][] flipAndInvertImageInPlace(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return new int[0][];

        final int C = A[0].length;
        for (int[] row : A) {
            flipAndInvertImage(row, C);
        }
        return A;
    }

    private static void flipAndInvertImage(final int[] row, final int len) {
        for (int i = 0; i < ((len + 1) >> 1); i++) {
            int tmp = row[i] ^ 1;
            row[i] = row[len - 1 - i] ^ 1;
            row[len - 1 - i] = tmp;
        }
    }
}
