package com.leetcode;

/*
https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
Medium. Array.

We are given an array A of positive integers,
and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of
the maximum array element in that subarray is at least L and at most R.

Example:
Input:
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements:
[2], [2, 1], [3].

Note:

L, R and A[i] will be an integer in the range [0, 10^9].
The length of A will be in the range of [1, 50000].
*/

class _795_NumberOfSubarraysWithBoundedMaximum {

    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0 || L > R) return 0;

        int start = 0, count = 0, res = 0;

        for (int end = 0; end < A.length; end++) {
            if (A[end] >= L && A[end] <= R) {
                count = end - start + 1;
                res += count;
            } else if (A[end] < L) {
                // count([start, end]) = count([start, end - 1])
                res += count;
            } else {
                // A[end] > R
                start = end + 1;
                count = 0;
            }
        }
        return res;
    }
}
