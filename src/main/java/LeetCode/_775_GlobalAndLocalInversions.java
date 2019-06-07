package LeetCode;

/*
https://leetcode.com/problems/global-and-local-inversions/
Medium. Array, Math.

We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1, 0, 2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.

Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.

Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.
*/

class _775_GlobalAndLocalInversions {

    public static boolean isIdealPermutation(int[] A) {
        if (A == null || A.length <= 1) return true;

        // All local inversions are global inversions
        // If the number of global inversions is equal to the number of local inversions,
        // it means that all global inversions in permutations are local inversions
        // It also means that we can not find A[i] > A[j] with i + 2 <= j
        // In other words, max(A[i]) < A[i + 2]
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            // swap local inversion (reverse pairs)
            if (max > A[i + 2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIdealPermutation2(int[] A) {
        if (A == null || A.length <= 1) return true;

        // All local inversions are global inversions
        // after sorting, A[i] would be placed at A[i - 1], A[i] or A[i + 1]
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}
