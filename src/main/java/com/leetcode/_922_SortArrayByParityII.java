package com.leetcode;

/*
https://leetcode.com/problems/sort-array-by-parity-ii/
Easy. Array, Sort.

Given an array A of non-negative integers, half of the integers in A are odd,
and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd;
and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

Note:

 2 <= A.length <= 20000
 A.length % 2 == 0
 0 <= A[i] <= 1000
*/

class _922_SortArrayByParityII {

    public static int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length == 0) return new int[0];

        for (int i = 0, j = 1; i < A.length; i += 2) {
            if ((A[i] & 0x1) == 0x0) continue;
            // assert j < A.length;
            while ((A[j] & 0x1) == 0x1) {
                j += 2;
            }
            swap(A, i, j);
        }
        return A;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
