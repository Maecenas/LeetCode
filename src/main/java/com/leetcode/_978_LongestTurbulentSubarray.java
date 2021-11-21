package com.leetcode;

/*
https://leetcode.com/problems/longest-turbulent-subarray/
Medium. Array, Dynamic Programming, Sliding Window.

A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.

That is, the subarray is turbulent if the comparison sign flips between each
adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])

Example 2:

Input: [4,8,12,16]
Output: 2

Example 3:

Input: [100]
Output: 1

Note:

1 <= A.length <= 40000
0 <= A[i] <= 10^9
*/

/**
 * @see _53_MaximumSubarray
 */
class _978_LongestTurbulentSubarray {

    public static int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) return 0;
        else if (A.length == 1) return 1;

        final int len = A.length;

        int res = 1, start = 0;
        for (int end = 1; end < len; end++) {
            if (A[end - 1] == A[end]) {
                start = end;
            } else if (end == len - 1 || A[end] == A[end + 1] || A[end - 1] < A[end] == A[end] < A[end + 1]) {
                // [start, end]
                res = Math.max(res, end - start + 1);
                start = end;
            }
        }
        return res;
    }

    public static int maxTurbulenceSize2(int[] A) {
        if (A == null || A.length == 0) return 0;
        else if (A.length == 1) return 1;

        // the length of current valid sequence which ends with two ascending numbers
        int asc = 1;
        // the length of current valid sequence which ends with two descending numbers
        int desc = 1;
        int res = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                desc = asc + 1;
                asc = 1;
            } else if (A[i] > A[i - 1]) {
                asc = desc + 1;
                desc = 1;
            } else {
                asc = 1;
                desc = 1;
            }
            res = Math.max(res, Math.max(asc, desc));
        }
        return res;
    }
}
