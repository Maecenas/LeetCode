package LeetCode;

/*
https://leetcode.com/problems/spiral-matrix/
Medium. Array.

Given a matrix of m x n elements (m rows, n columns),
return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

import java.util.ArrayList;
import java.util.List;

class _54_SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();

        int R = matrix.length - 1, C = matrix[0].length - 1, r = 0, c = 0;
        final ArrayList<Integer> res = new ArrayList<>((R + 1) * (C + 1));

        while (r <= R && c <= C) {
            // (r, 0) -> (r, C)
            for (int w = c; w <= C; w++) {
                res.add(matrix[r][w]);
            }
            // (r + 1, C) -> (R, C)
            for (int h = r + 1; h <= R; h++) {
                res.add(matrix[h][C]);
            }
            // (R, C - 1) -> (R, c)
            if (r != R) {
                for (int w = C - 1; w >= c; w--) {
                    res.add(matrix[R][w]);
                }
            }
            // (R - 1, c) -> (r + 1, c)
            if (c != C) {
                for (int h = R - 1; h > r; h--) {
                    res.add(matrix[h][c]);
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
