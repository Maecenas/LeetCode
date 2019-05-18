package LeetCode;

/*
https://leetcode.com/problems/largest-number-at-least-twice-of-others/
Easy. Array.

In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as
every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.

Example 2:

Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.

Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].
*/

class _747_LargestNumberAtLeastTwiceOfOthers {

    public static int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        else if (nums.length == 1) return 0;

        int max1 = 0, max2 = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max1]) {
                max2 = max1;
                max1 = i;
            } else if (i != max1 && nums[i] > nums[max2]) {
                max2 = i;
            }
        }
        if ((nums[max1] >> 1) >= nums[max2]) {
            return max1;
        }
        return -1;
    }
}
