package LeetCode;

/*
https://leetcode.com/problems/transpose-matrix/
Easy. Array.

Given a matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over it's main diagonal,
switching the row and column indices of the matrix.

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]

Example 2:

Input: [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]

Note:

1 <= A.length <= 1000
1 <= A[0].length <= 1000
*/

class _867_TransposeMatrix {

    public static int[][] transpose(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return new int[0][];

        final int R = A.length, C = A[0].length;
        final int[][] res = new int[C][R];

        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                res[c][r] = A[r][c];
        return res;
    }
}
