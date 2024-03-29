package com.leetcode;

/*
https://leetcode.com/problems/rotate-image/
Medium. Array.

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify
the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

class _48_RotateImage {

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix.length != matrix[0].length) return;

        final int N = matrix.length;

        // flip vertically
        // accessing rows than columns is way faster due to caching
        int[] tmp;
        for (int i = 0; i < N >> 1; i++) {
            tmp = matrix[i];
            matrix[i] = matrix[N - 1 - i];
            matrix[N - 1 - i] = tmp;
        }
        // transpose the matrix
        int temp;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
