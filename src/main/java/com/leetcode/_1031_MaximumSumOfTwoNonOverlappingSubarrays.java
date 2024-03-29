package com.leetcode;

/*
https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
Medium. Array.

Given an array A of non-negative integers, return the maximum sum of elements
in two non-overlapping (contiguous) subarrays, which have lengths L and M.
(For clarification, the L-length subarray could occur before or after the
M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1])
+ (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.

Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.

Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.

Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.

Note:

L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
*/

class _1031_MaximumSumOfTwoNonOverlappingSubarrays {

    /**
     * O(n), O(1)
     */
    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if (A == null || A.length < L + M || L < 1 || M < 1) return 0;

        // calculating prefix sum
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }

        int res = A[L + M - 1];
        // max sum of contiguous L elements before the last M elements
        int maxL = A[L - 1];
        // max sum of contiguous M elements before the last L elements
        int maxM = A[M - 1];
        for (int i = L + M; i < A.length; i++) {
            maxL = Math.max(maxL, A[i - M] - A[i - L - M]);
            maxM = Math.max(maxM, A[i - L] - A[i - L - M]);
            res = Math.max(res, Math.max(maxL + A[i] - A[i - M], maxM + A[i] - A[i - L]));
        }

        //// restore modified input
        //for (int i = A.length - 1; i > 0; i--) {
        //    A[i] -= A[i - 1];
        //}

        return res;
    }

    public static int maxSumTwoNoOverlap2(int[] A, int L, int M) {
        if (A == null || A.length < L + M || L < 1 || M < 1) return 0;

        final int[] sum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sum[i + 1] = sum[i] + A[i];
        }
        return Math.max(maxSum(sum, L, M), maxSum(sum, M, L));
    }

    private static int maxSum(final int[] sum, int L, int M) {
        int res = 0;
        for (int i = L + M, maxL = 0; i < sum.length; i++) {
            // update max of L-length subarray
            maxL = Math.max(maxL, sum[i - M] - sum[i - M - L]);
            // update max of the sum of L - length & M - length subarrays
            res = Math.max(res, maxL + sum[i] - sum[i - M]);
        }
        return res;
    }
}
