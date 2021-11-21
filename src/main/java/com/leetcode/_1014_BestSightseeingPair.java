package com.leetcode;

/*
https://leetcode.com/problems/best-sightseeing-pair/
Medium. Array.

Given an array A of positive integers, A[i] represents the value of the i-th
sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j): the sum
of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11

Note:

2 <= A.length <= 50000
1 <= A[i] <= 1000
*/

class _1014_BestSightseeingPair {

    public static int maxScoreSightseeingPair(int[] A) {
        if (A == null || A.length < 2) return 0;

        // curr is the best score that we have met
        int res = 0, curr = 0;
        for (int num : A) {
            res = Math.max(res, curr + num);
            curr = Math.max(curr, num) - 1;
        }
        return res;
    }
}
