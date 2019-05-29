package LeetCode;

/*
https://leetcode.com/problems/merge-intervals/
Medium. Array, Sort.

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019.
Please reset to default code definition to get new method signature.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class _56_MergeIntervals {

    /**
     * O(nlogn), O(1)/ O(n)
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        else if (intervals.length == 1) return intervals;

        // sort input based on start
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        final ArrayList<int[]> res = new ArrayList<>();
        int[] prev = intervals[0], curr;

        for (int i = 1; i < intervals.length; i++) {
            curr = intervals[i];
            if (curr[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                res.add(prev);
                prev = curr;
            }
        }
        res.add(prev);

        return res.toArray(new int[0][]);
    }

    public static int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        else if (intervals.length == 1) return intervals;

        // sort start and end respectively
        final int len = intervals.length;
        final int[] start = new int[len], end = new int[len];

        for (int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        final ArrayList<int[]> res = new ArrayList<>();

        int i = 0, j = 0;
        while (j < len) {
            // for the final interval, the latter one's start
            // must > previous one's end
            if (j == len - 1 || start[j + 1] > end[j]) {
                res.add(new int[]{start[i], end[j]});
                i = j + 1;
            }
            j++;
        }

        return res.toArray(new int[0][]);
    }
}
