package LeetCode;

/*
https://leetcode.com/problems/first-missing-positive/
Hard. Array.

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:

Input: [3,4,-1,1]
Output: 2

Example 3:

Input: [7,8,9,11,12]
Output: 1

Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/

import java.util.HashSet;

/**
 * @see _268_MissingNumber
 * @see _287_FindTheDuplicateNumber
 * @see _448_FindAllNumbersDisappearedInAnArray
 */
class _41_FirstMissingPositive {

    /**
     * O(n), O(n)
     */
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        else if (nums.length == 1) return nums[0] == 1 ? 2 : 1;

        final HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }

        for (int num = 1; num <= set.size() + 1; num++) {
            if (!set.contains(num)) {
                return num;
            }
        }
        return -1;
    }

    /**
     * O(n), O(1)
     */
    public static int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        else if (nums.length == 1) return nums[0] == 1 ? 2 : 1;

        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) {
                i++;
            } else if (nums[nums[i] - 1] != nums[i]) {
                // valid num is in [1, len] -> [0, len - 1]
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        i = 0;
        while (i < nums.length && nums[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}