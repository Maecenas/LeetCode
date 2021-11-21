package com.leetcode;

/*
https://leetcode.com/problems/height-checker/
Easy. Array.

Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students not standing in the right positions.
(This is the number of students that must move in order for all students
to be standing in non-decreasing order of height.)

Example 1:

Input: [1,1,4,2,1,3]
Output: 3
Explanation:
Students with heights 4, 3 and the last 1 are not standing in the right positions.

Note:

1 <= heights.length <= 100
1 <= heights[i] <= 100
*/

import java.util.Arrays;

class _1051_HeightChecker {

    private static final int R = 101;  // range of elements

    /**
     * O(nlogn), O(n)
     */
    public static int heightChecker(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        final int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);

        int res = 0;
        for (int i = 0; i < copy.length; i++) {
            if (heights[i] != copy[i]) res++;
        }
        return res;
    }

    /**
     * O(n), O(1)
     */
    public static int heightChecker2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        final int[] map = new int[R];
        for (int num : heights) {
            map[num]++;
        }

        // PriorityQueue like
        int res = 0, curr = 0;
        for (int num : heights) {
            while (map[curr] == 0) {
                curr++;
            }
            if (curr != num) {
                res++;
            }
            map[curr]--;
        }
        return res;
    }
}
