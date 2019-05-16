package LeetCode;

/*
https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
Easy. Array.

Given an integer array, you need to find one continuous subarray that
if you only sort this subarray in ascending order, then the whole array
will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:

Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order
to make the whole array sorted in ascending order.

Note:

Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/

class _581_ShortestUnsortedContinuousSubarray {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        final int n = nums.length;
        int lo = 0, hi = -1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        // find high as the rightmost index of valley, low as of leftmost peak
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[n - 1 - i]);
            if (nums[i] < max) {
                hi = i;
            } else {
                max = nums[i];
            }
            if (nums[n - 1 - i] > min) {
                lo = n - 1 - i;
            } else {
                min = nums[n - 1 - i];
            }
        }

        return (hi - lo > 0) ? (hi - lo + 1) : 0;
    }
}
