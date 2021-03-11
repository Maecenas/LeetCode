package LeetCode;

/*
https://leetcode.com/problems/maximal-square/
Medium. Dynamic Programming.

Given a 2D binary matrix filled with 0's and 1's, find the largest
square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

class _221_MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        final int R = matrix.length, C = matrix[0].length;
        // final int[][] dp = new int[R + 1][C + 1];
        // final int[][] dp = new int[2][C + 1];
        final int[] dp = new int[C + 1];
        int res = 0, prev = 0;
        // for (int i = 1; i <= R; i++) {
        //     for (int j = 1; j <= C; j++) {
        //         int tmp = dp[i % 2][j];
        //         if (matrix[(i - 1) % 2][j - 1] == '1') {
        //             dp[i % 2][j] = 1 + Math.min(Math.min(
        //                     dp[i % 2][j - 1],
        //                     dp[(i - 1) % 2][j]),
        //                     dp[(i - 1) % 2][j - 1]
        //             );
        //             res = Math.max(res, dp[i % 2][j]);
        //         } else {
        //             dp[i % 2][j] = 0;
        //         }
        //         prev = tmp;
        //     }
        // }
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                int tmp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = 1 + Math.min(Math.min(
                            dp[j - 1],
                            prev),
                            dp[j]);
                    res = Math.max(res, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = tmp;
            }
        }
        return res * res;
    }
}
