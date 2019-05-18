package LeetCode;

/*
https://leetcode.com/problems/toeplitz-matrix/
Easy. Array.

A matrix is Toeplitz if every diagonal from top-left to bottom-right
has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.

Example 1:

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.

Example 2:

Input:
matrix = [
  [1,2],
  [2,2]
]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.

Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

Follow up:

What if the matrix is stored on disk, and the memory is limited such that
you can only load at most one row of the matrix into the memory at once?

What if the matrix is so large that you can only load up a partial row
into the memory at once?
*/

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.LinkedList;
import java.util.List;

class _766_ToeplitzMatrix {

    /**
     * Compare With Top-Left Neighbor
     * O(M * N), O(1)
     */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return true;

        final int R = matrix.length, C = matrix[0].length;
        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                if (matrix[r - 1][c - 1] != matrix[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Follow up: we can only load up a row into the memory at once
     *     In other words, matrix are stream split by rows, and can be read multiple times
     *     We can store a buffer row for last row
     * Follow-up: we can only load up a partial row into the memory at once
     *     Divide and Conquer: We could split the whole matrix vertically into several sub-matrices,
     *     while each sub-matrix should have one column overlapped.
     *     We can repeat the same method in follow-up 1 for each sub-matrix.
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return true;

        final int R = matrix.length, C = matrix[0].length;
        final LinkedList<Integer> prev = new LinkedList<>();
        for (int c = 0; c < C; c++) {
            prev.add(matrix[0][c]);
        }

        for (int[] row : matrix) {
            for (int c = 1; c < C; c++) {
                if (prev.get(c - 1) != row[c]) return false;
            }
            prev.removeLast();
            prev.addFirst(row[0]);
        }
        return true;
    }
}
