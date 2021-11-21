package com.leetcode;

/*
https://leetcode.com/problems/maximize-distance-to-closest-person/
Easy. Array.

In a row of seats, 1 represents a person sitting in that seat,
and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him
and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.

Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
*/

class _849_MaximizeDistanceToClosestPerson {

    public static int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length < 2) return -1;

        final int len = seats.length;
        int prev = -1, res = 0;

        for (int i = 0; i < len; i++) {
            if (seats[i] == 0) continue;
            if (prev == -1) {
                res = Math.max(res, i);
            } else {
                res = Math.max(res, (i - prev) >> 1);
            }
            prev = i;
        }

        if (seats[len - 1] == 0) {
            res = Math.max(res, len - 1 - prev);
        }
        return res;
    }
}
