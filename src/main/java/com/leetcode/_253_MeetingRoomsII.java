package com.leetcode;

/*
https://leetcode.com/problems/meeting-rooms-ii/
Medium. Heap, Greedy, Sort.

Given an array of meeting time intervals intervals where intervals[i] =
[starti, endi], return the minimum number of conference rooms required.

Example 1:
 Input: intervals = [[0,30],[5,10],[15,20]]
 Output: 2

Example 2:
 Input: intervals = [[7,10],[2,4]]
 Output: 1

Constraints:
 1 <= intervals.length <= 10^4
 0 <= starti < endi <= 10^6
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see _56_MergeIntervals
 * @see _252_MeetingRooms
 * @see _452_MinimumNumberOfArrowsToBurstBalloons
 * @see _1094_CarPooling
 */
class _253_MeetingRoomsII {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length > 1e4) return 0;
        else if (intervals.length == 1) return 1;

        final PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        int res = 0;
        for (int[] i : intervals) {
            while (!pq.isEmpty() && pq.peek()[1] <= i[0]) {
                pq.poll();
            }
            pq.add(i);
            res = Math.max(res, pq.size());
        }
        return res;
    }

    public static int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length > 1e4) return 0;
        else if (intervals.length == 1) return 1;

        final int len = intervals.length;
        final int[] start = new int[len], end = new int[len];

        for (int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0;
        for (int i = 0, j = 0; i < len; i++) {
            // free a room
            if (start[i] >= end[j]) {
                res--;
                j++;
            }
            res++;
        }
        return res;
    }
}
