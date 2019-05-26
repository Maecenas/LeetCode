package LeetCode;

/*
https://leetcode.com/problems/insert-interval/
Hard. Array, Sort.

Given a set of non-overlapping intervals, insert a new interval
into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

NOTE: input types have been changed on April 15, 2019.
Please reset to default code definition to get new method signature.
*/

import java.util.ArrayList;
import java.util.Arrays;

class _57_InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length != 2) return intervals;
        else if (intervals == null || intervals.length == 0) return new int[][]{newInterval};

        final int len = intervals.length;
        final ArrayList<int[]> res = new ArrayList<>();

        int i = 0;
        // add all the intervals that ends before newInterval starts
        while (i < len && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }
        // merge all overlapping intervals with newInterval
        final int[] val = Arrays.copyOf(newInterval, 2);
        while (i < len && intervals[i][0] <= newInterval[1]) {
            val[0] = Math.min(val[0], intervals[i][0]);
            val[1] = Math.max(val[1], intervals[i][1]);
            i++;
        }
        res.add(val);
        // add all the rest intervals that start after new Interval ends
        while (i < len) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[0][]);
    }

    /**
     * Return the largest index i that nums[i][idx] < target, -1 if not found
     * nums[][idx] must be sorted
     * Not worth implementing for this question
     */
    private static int binarySearch(final int[][] nums, final int target, final int idx) {
        if (nums[0][idx] >= target) return -1;

        int lo = 0, hi = nums.length, mid;

        while (lo + 1 < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (nums[mid][idx] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
