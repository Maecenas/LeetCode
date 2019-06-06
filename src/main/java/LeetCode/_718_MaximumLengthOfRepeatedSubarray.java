package LeetCode;

/*
https://leetcode.com/problems/maximum-length-of-repeated-subarray/
Medium. Array, Hash Table, Binary Search, Dynamic Programming.

Given two integer arrays A and B, return the maximum length of an subarray
that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation:
The repeated subarray with maximum length is [3, 2, 1].

Note:

1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
*/

class _718_MaximumLengthOfRepeatedSubarray {

    /**
     * O(m * n), O(m * n)
     * Naive Dynamic Programming solution
     */
    public static int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;

        final int[][] dp = new int[A.length + 1][B.length + 1];
        int res = 0;

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * O(m * n), O(1)
     * Optimized DP to traverse the matrix diagonally
     */
    public static int findLength2(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;

        int res = 0;

        for (int start = 0; start < B.length; start++) {
            int maxLenEnding = 0;
            for (int i = 0, j = start; i < A.length && j < B.length; i++, j++) {
                if (A[i] != B[j]) {
                    maxLenEnding = 0;
                } else {
                    maxLenEnding++;
                    res = Math.max(res, maxLenEnding);
                }
            }
        }

        for (int start = 1; start < A.length; start++) {
            int maxLenEnding = 0;
            for (int i = start, j = 0; i < A.length && j < B.length; i++, j++) {
                if (A[i] != B[j]) {
                    maxLenEnding = 0;
                } else {
                    maxLenEnding++;
                    res = Math.max(res, maxLenEnding);
                }
            }
        }

        return res;
    }
}
