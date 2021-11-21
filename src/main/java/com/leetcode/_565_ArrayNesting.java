package com.leetcode;

/*
https://leetcode.com/problems/array-nesting/
Medium. Array.

A zero-indexed array A of length N contains all integers from 0 to N-1.
Find and return the longest length of set S, where S[i] =
{A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

Suppose the first element in S starts with the selection of element A[i]
of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]…
By that analogy, we stop adding right before a duplicate element occurs in S.

Example 1:

Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}

Note:

N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of A is an integer within the range [0, N - 1].
*/

class _565_ArrayNesting {

    public static int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) continue;

            int count = 0;
            int start = nums[i];
            while (nums[start] != -1) {
                int next = nums[start];
                count++;
                nums[start] = -1;
                start = next;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public static int arrayNesting2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] >> 15) != 0x0) continue;

            int count = 0;
            int start = nums[i];
            while ((nums[start] >> 15) == 0) {
                int next = nums[start];
                count++;
                nums[start] |= 0x8000;  // 1 << 15
                start = next;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
