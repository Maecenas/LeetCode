package com.leetcode;

/*
https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
Hard. Binary Search, Dynamic Programming, Queue.

Given a non-empty 2D matrix matrix and an integer k, find the max sum
of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).

Note:

The rectangle inside the matrix must have an area > 0.

Follow up: What if the number of rows is much larger than the number of columns?
*/

import java.util.TreeSet;

/**
 * @see _560_SubarraySumEqualsK
 * @see _1074_NumberOfSubmatricesThatSumToTarget
 */
class _363_MaxSumOfRectangleNoLargerThanK {

    /**
     * O(m * n^2), O(m)
     * Transpose the matrix when necessary for the time-space trade-off
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        final int C = matrix[0].length;

        // calculating prefix sum
        for (int[] row : matrix) {
            for (int c = 1; c < C; c++) {
                row[c] += row[c - 1];
            }
        }

        final TreeSet<Integer> set = new TreeSet<>();

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < C; i++) {
            for (int j = i; j < C; j++) {
                set.clear();
                set.add(0);
                int cur = 0;

                for (int[] row : matrix) {
                    cur += row[j] - (i >= 1 ? row[i - 1] : 0);
                    // TreeSet.ceiling may throw NullPointerException
                    //res = Math.max(res, set.ceiling(cur - k));
                    Integer ceiling = set.floor(cur - k);
                    if (ceiling != null) {
                        res = Math.max(res, cur - ceiling);
                    }
                    // early return
                    if (res == k) return k;
                    set.add(cur);
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
