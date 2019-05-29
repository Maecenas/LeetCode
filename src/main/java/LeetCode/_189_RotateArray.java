package LeetCode;

/*
https://leetcode.com/problems/rotate-array/
Easy. Array.

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Note:

Try to come up as many solutions as you can, there are at least 3 different ways
to solve this problem. Could you do it in-place with O(1) extra space?
*/

class _189_RotateArray {

    public static void rotateCyclics(int[] nums, int k) {
        int len = nums.length;
        int count = 0, curr, prev, next, tmp;
        for (int start = 0; count < len; start++) {
            curr = start;
            prev = nums[start];
            do {
                next = (curr + k) % len;
                tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                curr = next;
                count++;
            } while (start != curr);
        }
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) return;

        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
