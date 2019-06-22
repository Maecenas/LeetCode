package LeetCode;

/*
https://leetcode.com/problems/trapping-rain-water/
Hard. Array, Two Pointers, Dynamic Programming, Stack.

Given n non-negative integers representing an elevation map
where the width of each bar is 1,
compute how much water it is able to trap after raining.

The above elevation map is represented by array
[0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.
Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

import java.util.Stack;

/**
 * @see _11_ContainerWithMostWater
 * @see _238_ProductOfArrayExceptSelf
 */
class _42_TrappingRainWater {

    /**
     * O(n^2), O(1)
     * Brute force
     */
    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        final int len = height.length;
        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            // search the left part for max bar size
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            // search the right part for max bar size
            for (int j = i; j < len; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    /**
     * O(n), O(n)
     * Dynamic Programming
     */
    public static int trap2(int[] height) {
        if (height == null || height.length < 3) return 0;

        final int len = height.length;
        final int[] maxLeft = new int[len], maxRight = new int[len];

        maxLeft[0] = height[0];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        maxRight[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            res += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return res;
    }

    /**
     * O(n), O(n)
     * Stack
     */
    public static int trap3(int[] height) {
        if (height == null || height.length < 3) return 0;

        // minimum value so far (decreasing stack)
        final Stack<Integer> stack = new Stack<>();

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty()) break;
                int width = i - stack.peek() - 1;
                int minHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                res += width * minHeight;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * O(n), O(1)
     * Two Pointers
     */
    public static int trap4(int[] height) {
        if (height == null || height.length < 3) return 0;

        int left = 0, right = height.length - 1, maxLeft = 0, maxRight = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    res += (maxLeft - height[left]);
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    res += (maxRight - height[right]);
                }
                right--;
            }
        }
        return res;
    }
}
