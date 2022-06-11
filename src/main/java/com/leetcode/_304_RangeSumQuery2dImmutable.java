package com.leetcode;

/*
https://leetcode.com/problems/range-sum-query-2d-immutable/
Medium. Array, Design, Matrix, Prefix Sum.

Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by
its upper left corner (row1, col1) and lower right corner (row2, col2).

Implement the NumMatrix class:

 NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the
  elements of matrix inside the rectangle defined by its upper left corner (row1,
  col1) and lower right corner (row2, col2).

Example 1:
 Input
  ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
  [
   [[[3, 0, 1, 4, 2],
     [5, 6, 3, 2, 1],
     [1, 2, 0, 1, 5],
     [4, 1, 0, 1, 7],
     [1, 0, 3, 0, 5]]],
   [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
 Output
  [null, 8, 11, 12]

 Explanation
  NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
  numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
  numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
  numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

Constraints:
 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 200
 -10^4 <= matrix[i][j] <= 10^4
 0 <= row1 <= row2 < m
 0 <= col1 <= col2 < n
 At most 10⁴ calls will be made to sumRegion.

Your NumMatrix object will be instantiated and called as such:

 NumMatrix obj = new NumMatrix(matrix);
 int param_1 = obj.sumRegion(row1,col1,row2,col2);
*/

/**
 * @see _303_RangeSumQueryImmutable
 */
class _304_RangeSumQuery2dImmutable {

    static class NumMatrix {

        private final int[][] prefixSum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix.length > 200
                    || matrix[0].length == 0 || matrix[0].length > 200) throw new IllegalArgumentException();

            final int R = matrix.length, C = matrix[0].length;
            prefixSum = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    prefixSum[r][c] = matrix[r][c]
                            + (r >= 1 ? prefixSum[r - 1][c] : 0)
                            + (c >= 1 ? prefixSum[r][c - 1] : 0)
                            - (r >= 1 && c >= 1 ? prefixSum[r - 1][c - 1] : 0);
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || row2 >= prefixSum.length || row1 > row2
                    || col1 < 0 || col2 >= prefixSum[0].length || col1 > col2) throw new IllegalArgumentException();
            return prefixSum[row2][col2]
                    - (col1 >= 1 ? prefixSum[row2][col1 - 1] : 0)
                    - (row1 >= 1 ? prefixSum[row1 - 1][col2] : 0)
                    + (row1 >= 1 && col1 >= 1 ? prefixSum[row1 - 1][col1 - 1] : 0);
        }
    }

    static class NumMatrix2 {

        private final int[][] prefixSum;

        public NumMatrix2(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix.length > 200
                    || matrix[0].length == 0 || matrix[0].length > 200) throw new IllegalArgumentException();

            final int R = matrix.length, C = matrix[0].length;
            // prefix[r][c]: sum of matrix[0:r - 1][0:c - 1]
            prefixSum = new int[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    prefixSum[r][c] = matrix[r - 1][c - 1]
                            + prefixSum[r - 1][c]
                            + prefixSum[r][c - 1]
                            - prefixSum[r - 1][c - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || row2 >= prefixSum.length - 1 || row1 > row2
                    || col1 < 0 || col2 >= prefixSum[0].length - 1 || col1 > col2) throw new IllegalArgumentException();

            return prefixSum[row2 + 1][col2 + 1]
                    - prefixSum[row2][col1 + 1]
                    - prefixSum[row1 + 1][col2]
                    + prefixSum[row1][col1];
        }
    }
}
