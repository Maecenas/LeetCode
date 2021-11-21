package com.leetcode;

/*
https://leetcode.com/problems/non-overlapping-intervals/
Medium. Greedy.

Given a collection of intervals, find the minimum number of intervals you need
to remove to make the rest of the intervals non-overlapping.

Example 1:
 Input: [[1,2],[2,3],[3,4],[1,3]]
 Output: 1
 Explanation: [1,3] can be removed and the rest of intervals
  are non-overlapping.

Example 2:
 Input: [[1,2],[1,2],[1,2]]
 Output: 2
 Explanation: You need to remove two [1,2] to make the rest of intervals
  non-overlapping.

Example 3:
 Input: [[1,2],[2,3]]
 Output: 0
 Explanation: You don't need to remove any of the intervals since they're
  already non-overlapping.

Note:
 You may assume the interval's end point is always bigger than its start point.
 Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap
  each other.
*/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @see _452_MinimumNumberOfArrowsToBurstBalloons
 */
class _435_NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return 0;

        // Not working when one could cover another, like [[0, 3], [1, 2]]
        //Arrays.sort(intervals,
        //        Comparator.comparingInt((int[] i) -> i[0])
        //                .thenComparingInt(i -> i[1])
        //);
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        int res = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                end = Math.min(end, intervals[i][1]);
                res++;
            } else {
                end = intervals[i][1];
            }
        }
        return res;
    }
}
