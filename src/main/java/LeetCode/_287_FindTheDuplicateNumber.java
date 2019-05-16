package LeetCode;

/*
https://leetcode.com/problems/find-the-duplicate-number/
Medium. Array, Two Pointers, Binary Search.

Given an array nums containing n + 1 integers where each integer is between
1 and n (inclusive), prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2

Example 2:

Input: [3,1,3,4,2]
Output: 3

Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

class _287_FindTheDuplicateNumber {

    /**
     * Copy nums[i] to nums[nums[i]], if already present then is duplicate
     * The general intuition of finding duplicate/missing number in array is
     * the mapping between [0, n - 1] and [1, n]. Modify input.
     * O(n), O(1)
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        else if (nums.length == 2) return nums[0];

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * Not modify original data
     * O(nlogn), O(n)
     */
    public static int findDuplicateMap(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        else if (nums.length == 2) return nums[0];

        int[] map = new int[nums.length];
        for (int item : nums) {
            if (map[item] >= 1) {
                return item;
            } else {
                map[item] = 1;
            }
        }
        return -1;
    }

    private static void swap(final int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * Using the idea of two pointer
     * O(n), O(1)
     *
     * @see _142_LinkedListCycleII
     */
    public static int findDuplicateFloydCycleDetection(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        else if (nums.length == 2) return nums[0];

        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int res = nums[0];
        while (res != slow) {
            slow = nums[slow];
            res = nums[res];
        }
        return res;
    }
}
