package LeetCode;

/*
https://leetcode.com/problems/largest-rectangle-in-histogram/
Hard. Array, Stack.

Given n non-negative integers representing the histogram's bar height
where the width of each bar is 1, find the area of largest rectangle
in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @see _85_MaximalRectangle
 */
class _84_LargestRectangleInHistogram {

    /**
     * O(n), O(n)
     * Stack
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        final int len = heights.length;
        // increasing stack to find the previous less element
        // LinkedList implementation is sometimes faster then Stack
        final Deque<Integer> stack = new ArrayDeque<>();

        int res = 0;
        // i == len is included, as we only count to i - 1
        for (int i = 0; i <= len; i++) {
            int curr = (i == len) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= curr) {
                int height = heights[stack.pop()];
                // [0, i) or (previous less element, i)
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * O(n), O(n)
     * Same idea as monotonous stack
     */
    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        final int len = heights.length;
        // index of the previous/next less element
        final int[] left = new int[len], right = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            int idx = i + 1;
            while (idx < len && heights[idx] >= heights[i]) {
                idx = right[idx];
            }
            right[i] = idx;
        }

        // Combine finding left (previous less element) with calculating area
        //for (int i = 0; i < len; i++) {
        //    int idx = i - 1;
        //    while (idx >= 0 && heights[idx] >= heights[i]) {
        //        idx = left[idx];
        //    }
        //    left[i] = idx;
        //    // height * (left[i], right[i])
        //    res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        //}

        for (int i = 0; i < len; i++) {
            int idx = i - 1;
            while (idx >= 0 && heights[idx] >= heights[i]) {
                idx = left[idx];
            }
            left[i] = idx;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            // height * (left[i], right[i]) (open interval)
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}
