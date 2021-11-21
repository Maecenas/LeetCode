package com.leetcode;

/*
https://leetcode.com/problems/car-pooling/
Medium.

You are driving a vehicle that has capacity empty seats initially available
for passengers. The vehicle only drives east
(ie. it cannot turn aroundand drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location]
contains information about the i-th trip: the number of passengers that must be
picked up, and the locations to pick them up and drop them off. The locations are
given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers
for all the given trips.

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true

Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
*/

import java.util.TreeMap;

/**
 * @see _253_MeetingRoomsII
 */
class _1094_CarPooling {

    private static final int R = 1001;  // range of elements

    public static boolean carPooling(int[][] trips, int capacity) {
        if (trips == null) return false;
        else if (trips.length == 0) return true;
        else if (capacity == 0) return false;

        final TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) - trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) + trip[0]);
        }
        for (int stop : map.values()) {
            capacity += stop;
            if (capacity < 0) return false;
        }
        return true;
    }

    public static boolean carPooling2(int[][] trips, int capacity) {
        if (trips == null) return false;
        else if (trips.length == 0) return true;
        else if (capacity == 0) return false;

        final int[] map = new int[R];
        for (int[] trip : trips) {
            map[trip[1]] -= trip[0];
            map[trip[2]] += trip[0];
        }

        for (int stop : map) {
            if (stop == 0) continue;
            capacity -= stop;
            if (capacity < 0) return false;
        }
        return true;
    }
}
