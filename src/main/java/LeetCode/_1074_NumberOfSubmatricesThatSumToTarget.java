package LeetCode;

/*
https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
Hard.

Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y]
with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different
if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
*/

import java.util.HashMap;

/**
 * @see _363_MaxSumOfRectangleNoLargerThanK
 * @see _560_SubarraySumEqualsK
 */
public class _1074_NumberOfSubmatricesThatSumToTarget {

    /**
     * O(m * n^2), O(m)
     * Reduce 2D sum target to 1D search (row + column)
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        final int C = matrix[0].length;

        // calculating prefix sum
        for (int[] row : matrix) {
            for (int c = 1; c < C; c++) {
                row[c] += row[c - 1];
            }
        }

        final HashMap<Integer, Integer> map = new HashMap<>();

        int res = 0;
        for (int i = 0; i < C; i++) {
            for (int j = i; j < C; j++) {
                map.clear();
                map.put(0, 1);
                int cur = 0;

                for (int[] row : matrix) {
                    cur += row[j] - (i >= 1 ? row[i - 1] : 0);
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }

        //// restore modified input
        //for (int[] row : matrix) {
        //    for (int c = C - 1; c >= 1; c--) {
        //        row[c] -= row[c - 1];
        //    }
        //}

        return res;
    }
}
