package LeetCode;

/*
https://leetcode.com/problems/non-decreasing-array/
Easy. Array.

Given an array with n integers, your task is to check if it could become
non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds
for every i (1 <= i < n).

Example 1:

Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:

Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

Note:
The n belongs to [1, 10,000].
*/

class _665_NonDecreasingArray {

    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        else if (nums.length <= 2) return true;

        int count = 0;
        for (int i = 1, prev = nums[0]; i < nums.length; i++) {
            // use prev to track the last correct value in the array
            if (prev > nums[i]) {
                if (count++ > 0) return false;
                // lower nums[i] to prev
                // presuming that there can only be one outlier
                if (i >= 2 && nums[i - 2] >= nums[i]) continue;
            }
            prev = nums[i];
        }
        return true;
    }
}
