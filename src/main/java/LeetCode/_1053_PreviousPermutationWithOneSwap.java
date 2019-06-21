package LeetCode;

/*
https://leetcode.com/problems/previous-permutation-with-one-swap/
Medium. Array, Greedy.

Given an array A of positive integers (not necessarily distinct), return the
lexicographically largest permutation that is smaller than A, that can be made
with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).
If it cannot be done, then return the same array.

Example 1:

Input: [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.

Example 2:

Input: [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.

Example 3:

Input: [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.

Example 4:

Input: [3,1,1,3]
Output: [1,3,1,3]
Explanation: Swapping 1 and 3.

Note:

1 <= A.length <= 10000
1 <= A[i] <= 10000
*/

class _1053_PreviousPermutationWithOneSwap {

    public static int[] prevPermOpt1(int[] A) {
        if (A == null || A.length == 0) return new int[0];

        final int len = A.length;
        int left = len - 2, right = len - 1;
        while (left >= 0 && A[left] <= A[left + 1]) {
            left--;
        }
        // early return
        if (left < 0) return A;
        while (A[right] >= A[left]) {
            right--;
        }
        // skip duplicates
        while (A[right - 1] == A[right]) {
            right--;
        }
        swap(A, left, right);
        return A;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
