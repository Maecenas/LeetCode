package com.leetcode;

/*
https://leetcode.com/problems/minimum-increment-to-make-array-unique/
Medium. Array.

Given an array of integers A, a move consists of choosing any A[i],
and incrementing it by 1.

Return the least number of moves to make every value in A unique.

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].

Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

Note:

0 <= A.length <= 40000
0 <= A[i] < 40000
*/

class _945_MinimumIncrementToMakeArrayUnique {

    private static final int R = 80000;  // range of final elements

    public static int minIncrementForUnique(int[] A) {
        if (A == null || A.length <= 1) return 0;

        final int[] count = new int[R];
        int max = 0;
        for (int i : A) {
            count[i]++;
            max = Math.max(max, i);
        }

        int res = 0, taken = 0;

        for (int i = 0; i < R; i++) {
            // early return
            if (i > max && taken == 0) break;
            if (count[i] >= 2) {
                taken += count[i] - 1;
                // number of moves choosing i
                res -= i * (count[i] - 1);
            } else if (taken > 0 && count[i] == 0) {
                // raise one element to i
                taken--;
                res += i;
            }
        }

        return res;
    }
}
