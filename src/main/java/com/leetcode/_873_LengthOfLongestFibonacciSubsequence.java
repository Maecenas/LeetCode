package com.leetcode;

/*
https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
Medium. Array, Dynamic Programming.

A sequence X_1, X_2, ..., X_n is fibonacci-like if:

n >= 3
X_i + X_{i+1} = X_{i+2} for all i + 2 <= n

Given a strictly increasing array A of positive integers forming a sequence,
find the length of the longest fibonacci-like subsequence of A.
If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting
any number of elements (including none) from A, without changing the order
of the remaining elements.
For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)

Example 1:

Input: [1,2,3,4,5,6,7,8]
Output: 5
Explanation:
The longest subsequence that is fibonacci-like: [1,2,3,5,8].

Example 2:

Input: [1,3,7,11,12,14,18]
Output: 3
Explanation:
The longest subsequence that is fibonacci-like:
[1,11,12], [3,11,14] or [7,11,18].

Note:

3 <= A.length <= 1000
1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
(The time limit has been reduced by 50% for submissions in Java, C, and C++.)
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @see _509_FibonacciNumber
 */
class _873_LengthOfLongestFibonacciSubsequence {

    /**
     * O(n^2), O(nlogM), where M is the largest element in A
     */
    public static int lenLongestFibSubseq(int[] A) {
        if (A == null || A.length < 3) return 0;

        final int len = A.length;
        final HashMap<Integer, Integer> index = new HashMap<>(), dp = new HashMap<>();
        for (int i = 0; i < len; i++) {
            index.put(A[i], i);
        }

        int res = 0;
        for (int k = 0; k < len; k++)
            for (int j = 0; j < k; j++) {
                // checking triplet (i, j, k) that i < j < k
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i < 0 || i >= j) continue;
                // encode tuple uniquely as (i, j) -> (i * N + j)
                int curr = dp.getOrDefault(i * len + j, 2) + 1;
                // update (j, k)
                dp.put(j * len + k, curr);
                res = Math.max(res, curr);
            }

        return res;
    }

    /**
     * O(n^2), O(n^2)
     */
    public static int lenLongestFibSubseq2(int[] A) {
        if (A == null || A.length < 3) return 0;

        final int len = A.length;
        final int[][] dp = new int[len][len];

        int res = 0;
        for (int k = 2; k < len; k++) {
            int i = 0, j = k - 1;
            while (i < j) {
                int sum = A[i] + A[j];
                if (sum < A[k]) {
                    i++;
                } else if (sum > A[k]) {
                    j--;
                } else {
                    dp[j][k] = dp[i][j] + 1;
                    res = Math.max(res, dp[j][k]);
                    j--;
                    i++;
                }
            }
        }

        return res == 0 ? 0 : res + 2;
    }
}
