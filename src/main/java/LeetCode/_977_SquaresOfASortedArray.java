package LeetCode;

/*
https://leetcode.com/problems/squares-of-a-sorted-array/
Easy. Array, Two Pointers.

Given an array of integers A sorted in non-decreasing order, return an array
of the squares of each number, also in sorted non-decreasing order.

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
*/

class _977_SquaresOfASortedArray {

    public static int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) return new int[0];

        final int[] res = new int[A.length];
        int lo = 0, hi = A.length - 1, i = A.length - 1;

        while (lo <= hi) {
            if (A[lo] >= 0) {
                while (i >= 0 && A[hi] > 0) {
                    res[i] = A[hi] * A[hi];
                    i--;
                    hi--;
                }
                break;
            } else if (A[hi] <= 0) {
                while (i >= 0 && A[lo] < 0) {
                    res[i] = A[lo] * A[lo];
                    i--;
                    lo++;
                }
                break;
            } else {
                // A[lo] < 0 && A[hi] > 0
                if (A[hi] >= -A[lo]) {
                    res[i] = A[hi] * A[hi];
                    hi--;
                    i--;
                } else {
                    res[i] = A[lo] * A[lo];
                    lo++;
                    i--;
                }
            }
        }

        while (i >= 0) {
            res[i] = 0;
            i--;
        }
        return res;
    }
}
