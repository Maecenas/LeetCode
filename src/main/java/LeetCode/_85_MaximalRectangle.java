package LeetCode;

/*
https://leetcode.com/problems/maximal-rectangle/
Hard. Array, Hash Table, Dynamic Programming, Stack.

Given a 2D binary matrix filled with 0's and 1's, find the largest
rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

import java.util.Arrays;

/**
 * @see _84_LargestRectangleInHistogram
 */
class _85_MaximalRectangle {

    /**
     * O(m * n), O(n)
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        final int n = matrix[0].length;

        final int[] height = new int[n], left = new int[n], right = new int[n];
        Arrays.fill(right, n - 1);

        int res = 0;
        // Think about the matrix as r histograms of [0, r] rows
        for (char[] row : matrix) {
            // find next less element with declaring variable only once
            // special case than the normal histogram in #84
            // as a '0' is a zero height bar, and we know a right cut-down
            int idx = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (row[i] == '0') {
                    right[i] = n;
                    idx = i - 1;
                } else {
                    // row[i] == '1'
                    right[i] = Math.min(right[i], idx);
                }
            }

            // Combine finding left (previous less element) with calculating area
            // Note that the previous left element is (left[i] - 1) here than in #84
            // This definition is meant to reduce initialization cost,
            // i.e., Arrays.fill() and int idx = i - 1;
            idx = 0;
            for (int i = 0; i < n; i++) {
                if (row[i] == '0') {
                    left[i] = 0;
                    height[i] = 0;
                    idx = i + 1;
                } else {
                    // row[i] == '1'
                    left[i] = Math.max(left[i], idx);
                    height[i]++;
                    // height * [left[i], right[i]] (closed interval)
                    res = Math.max(res, height[i] * (right[i] - left[i] + 1));
                }
            }
        }
        return res;
    }
}
