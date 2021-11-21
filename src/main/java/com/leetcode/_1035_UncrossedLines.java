package com.leetcode;

/*
https://leetcode.com/problems/uncrossed-lines/
Medium. Array.

We write the integers of A and B (in the order they are given)
on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting
two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.

Note that a connecting lines cannot intersect even at the endpoints:
each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

Example 1:

Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.

Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3

Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2

Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
*/

/**
 * @see _72_EditDistance
 */
class _1035_UncrossedLines {

    /**
     * O(m * n), O(m * n)
     */
    public static int maxUncrossedLines(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return 0;

        // find LCS (Longest Common Subsequence)
        // dp[i + 1][j + 1] = lcs(A[:i], B[:j])
        final int m = A.length, n = B.length;
        final int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * O(m * n), O(min(m, n))
     */
    public static int maxUncrossedLines2(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return 0;

        // make sure A is smaller
        if (B.length > A.length) {
            int[] tmp = A;
            A = B;
            B = tmp;
        }

        // find LCS (Longest Common Subsequence)
        // dp[j + 1] = lcs(A[:i], B[:j])
        final int n = B.length;
        final int[] dp = new int[n + 1];
        for (int num : A) {
            for (int j = n - 1; j >= 0; j--) {
                if (num == B[j]) {
                    dp[j + 1] = dp[j] + 1;
                }
            }
            for (int j = 0; j < n; j++) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j]);
            }
        }
        return dp[n];
    }
}
