package com.leetcode;

/*
https://leetcode.com/problems/container-with-most-water/
Medium. Array, Two Pointers.

Given n non-negative integers a1, a2, ..., an,
where each represents a point at coordinate (i, ai).

n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container,
such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
*/

/**
 * @see _42_TrappingRainWater
 */
class _11_ContainerWithMostWater {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int maxArea = 0, lo = 0, hi = height.length - 1;
        while (lo < hi) {
            maxArea = Math.max(maxArea, (hi - lo) * Math.min(height[lo], height[hi]));
            if (height[lo] > height[hi]) {
                hi--;
            } else {
                lo++;
            }
        }
        return maxArea;
    }
}
