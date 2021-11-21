package com.leetcode;

/*
https://leetcode.com/problems/meeting-rooms/
Easy. Sort.

Given an array of meeting time intervals where intervals[i] = [starti, endi],
determine if a person could attend all meetings.

Example 1:
 Input: intervals = [[0,30],[5,10],[15,20]]
 Output: false

Example 2:
 Input: intervals = [[7,10],[2,4]]
 Output: true

Constraints:
 0 <= intervals.length <= 10^4
 intervals[i].length == 2
 0 <= starti < endi <= 10^6
*/

import java.util.Arrays;
import java.util.Comparator;

/**
 * @see _56_MergeIntervals
 * @see _253_MeetingRoomsII
 */
class _252_MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return true;
        else if (intervals.length > 1e4) return false;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int prev = 0;
        for (int[] i : intervals) {
            if (i[0] < prev) return false;
            prev = i[1];
        }
        return true;
    }
}
