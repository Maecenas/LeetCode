package com.leetcode;

/*
https://leetcode.com/problems/maximum-width-ramp/
Medium. Array.

Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].
The width of such a ramp is j - i.

Find the maximum width of a ramp in A. If one doesn't exist, return 0.

Example 1:

Input: [6,0,8,2,1,5]
Output: 4
Explanation:
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.

Example 2:

Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation:
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.

Note:

2 <= A.length <= 50000
0 <= A[i] <= 50000
*/

import java.util.ArrayDeque;
import java.util.Deque;

class _962_MaximumWidthRamp {

    public static int maxWidthRamp(int[] A) {
        if (A == null || A.length <= 1) return 0;

        final int len = A.length;
        // LinkedList implementation is sometimes faster then Stack
        // minimum value so far (decreasing stack)
        final Deque<Integer> min = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (min.isEmpty() || A[min.peekLast()] > A[i]) {
                min.addLast(i);
            }
        }

        int res = 0;
        for (int i = len - 1; i > res; i--) {
            if (min.isEmpty()) break;
            while (!min.isEmpty() && A[min.peekLast()] <= A[i]) {
                res = Math.max(res, i - min.removeLast());
            }
        }
        return res;
    }
}
