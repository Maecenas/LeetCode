package LeetCode;

/*
https://leetcode.com/problems/corporate-flight-bookings/
Medium.

There are n flights, and they are labeled from 1 to n.

We have a list of flight bookings. The i-th booking bookings[i] = [i, j, k]
means that we booked k seats from flights labeled i to j inclusive.

Return an array answer of length n, representing the number of seats
booked on each flight in order of their label.

Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]

Constraints:

1 <= bookings.length <= 20000
1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
1 <= bookings[i][2] <= 10000
*/

class _1109_CorporateFlightBookings {

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        if (bookings == null || bookings.length == 0
                || bookings[0].length != 3) return new int[0];

        final int[] res = new int[n];

        // reserve k seats from i, release till j
        for (int[] booking : bookings) {
            res[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                res[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
