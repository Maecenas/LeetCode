package com.leetcode;

/*
https://leetcode.com/problems/kth-largest-element-in-an-array/
Medium. Array, Divide and Conquer, Sorting, Heap, Quickselect.

Find the kth largest element in an unsorted array. Note that it is the
kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @see _973_KClosestPointsToOrigin#kClosest2(int[][], int)
 */
class _215_KthLargestElementInAnArray {

    /**
     * O(nlogn), O(1)
     * Sorting
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) return -1;

        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * O(nlogk), O(k)
     * Min heap
     */
    public static int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length < k) return -1;

        final PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }

    /**
     * O(n) / O(n^2) worst, O(1)
     * Quick Select
     */
    public static int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length < k) return -1;
        else if (nums.length == 1 && k == 1) return nums[0];

        shuffle(nums);
        k = nums.length - k;  // k_th largest
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int j = partition(nums, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return nums[k];
        }
        return nums[k];
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * Knuth Shuffle
     */
    private static void shuffle(int[] nums) {
        final ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 1; i < nums.length; i++) {
            int r = rand.nextInt(i + 1);
            swap(nums, i, r);
        }
    }

    private static int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // find items on left to swap
            while (i < hi && nums[++i] < nums[lo]) {
                if (i == hi) break;
            }
            // find items on right to swap
            while (j > lo && nums[lo] < nums[--j]) {
                if (j == lo) break;
            }
            // check if pointers cross swap
            if (i >= j) break;
            swap(nums, i, j);
        }
        // swap with partitioning item
        swap(nums, lo, j);
        // return index of item now known to be in place
        return j;
    }

    public static int findKthLargest4(int[] nums, int k) {
        return Arrays.stream(nums)
                .sorted()
                .skip(nums.length - k)
                .limit(1)
                .sum();
    }
}
