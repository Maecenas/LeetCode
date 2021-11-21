package com.leetcode;

/*
https://leetcode.com/problems/sort-array-by-parity/
Easy. Array.

Given an array A of non-negative integers, return an array consisting of
all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000
*/

class _905_SortArrayByParity {

    public static int[] sortArrayByParity(int[] A) {
        if (A == null) return new int[0];

        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            while (lo < hi && (A[lo] & 0x1) == 0x0) lo++;
            while (lo < hi && (A[hi] & 0x1) == 0x1) hi--;
            if (lo < hi) {
                swap(A, lo, hi);
            }
        }
        return A;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
