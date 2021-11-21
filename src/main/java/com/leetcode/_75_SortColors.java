package com.leetcode;

/*
https://leetcode.com/problems/sort-colors/
Medium. Array, Two Pointers, Sort.

Given an array with n objects colored red, white or blue,
sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color
red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's,
then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

/**
 * See also: 3-way partitioning in quicksort
 * <a>https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Quick3way.java.html</a>
 */
class _75_SortColors {

    /**
     * quicksort 3-way partition
     * +------+---------+-------------+-------+
     * |  <p  |  =p     |   unseen .  |  > p  |
     * +------+---------+-------------+-------+
     * ↑          ↑           ↑
     * lt         i           gt
     * lt: 1st elem == pivot
     * i:  1st unseen elem
     * gt: last unseen elem
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // No need for a Knuth Shuffle for a better pivot
        //StdRandom.shuffle(a);
        int lt = 0, i = 0, gt = nums.length - 1;
        while (i <= gt) {
            if (nums[i] == 0) {
                swap(nums, lt++, i++);
            } else if (nums[i] == 2) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
