package com.leetcode;

/*
https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
Easy. Array.

In a list of songs, the i-th song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds
is divisible by 60. Formally, we want the number of indices i < j
with (time[i] + time[j]) % 60 == 0.

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.

Note:

1 <= time.length <= 60000
1 <= time[i] <= 500
*/

class _1010_PairsOfSongsWithTotalDurationsDivisibleBy60 {

    private static final int R = 60;  // given divisor

    public static int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length <= 1) return 0;

        final int[] count = new int[R];
        for (int t : time) {
            count[t % R]++;
        }

        int res = 0;
        for (int i = 0; i <= (R >> 1); i++) {
            if (i == 0 || (i << 1) == R) {
                // from n choose r: (n * (n - 1)) / 2
                res += (count[i] * (count[i] - 1)) >> 1;
            } else {
                res += (count[i] * count[R - i]);
            }
        }
        return res;
    }

    /**
     * One-pass solution that is even easier to write
     */
    public static int numPairsDivisibleBy60OnePass(int[] time) {
        if (time == null || time.length <= 1) return 0;

        final int[] count = new int[R];
        int res = 0;
        for (int t : time) {
            t %= R;
            // add % R in case that t == 0
            res += count[(R - t) % R];
            count[t % R]++;
        }
        return res;
    }
}
