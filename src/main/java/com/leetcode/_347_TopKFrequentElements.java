package com.leetcode;

/*
https://leetcode.com/problems/top-k-frequent-elements/
Medium. Array, Hash Table, Divide and Conquer, Sorting, Heap, Bucket Sort, Counting, Quickselect.

Given an integer array nums and an integer k, return the k most frequent
elements. You may return the answer in any order.

Example 1:
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]

Example 2:
 Input: nums = [1], k = 1
 Output: [1]

Constraints:
 1 <= nums.length <= 10^5
 k is in the range [1, the number of unique elements in the array].
 It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n),
where n is the array's size.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @see _215_KthLargestElementInAnArray#findKthLargest3
 * @see _973_KClosestPointsToOrigin#kClosest2
 */
class _347_TopKFrequentElements {

    /**
     * O(nlogk), O(n)
     * minHeap
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length > 1e5
                || k < 1 || k > nums.length) return new int[0];
        else if (k == nums.length) return nums;

        final Map<Integer, Integer> counter = count(nums);
        final PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparingInt(counter::get)
        );
        for (int num : counter.keySet()) {
            pq.add(num);
            if (pq.size() > k) pq.poll();
        }
        assert pq.size() == k;

        final int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }

    private static Map<Integer, Integer> count(final int[] nums) {
        final Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        return counter;
    }

    /**
     * O(n), O(n)
     * Bucket Sort
     */
    public static int[] topKFrequent2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length > 1e5
                || k < 1 || k > nums.length) return new int[0];
        else if (k == nums.length) return nums;

        final Map<Integer, Integer> counter = count(nums);

        final int n = nums.length;
        final List<Integer>[] freq = new List[n + 1];

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            final int count = entry.getValue();
            if (freq[count] == null) {
                freq[count] = new ArrayList<>();
            }
            freq[count].add(entry.getKey());
        }

        final int[] res = new int[k];
        for (int i = freq.length - 1, j = 0; i >= 0; i--) {
            if (freq[i] != null) {
                for (int num : freq[i]) {
                    res[j++] = num;
                }
                if (j == k) break;
            }
        }
        return res;
    }

    /**
     * O(n) / O(n^2) worst, O(n)
     * Quick Select
     */
    public static int[] topKFrequent3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length > 1e5
                || k < 1 || k > nums.length) return new int[0];
        else if (k == nums.length) return nums;

        final Map<Integer, Integer> counter = count(nums);

        final int[] elements = new int[counter.size()];
        int i = 0;
        for (int num : counter.keySet()) {
            elements[i++] = num;
        }
        final Comparator<Integer> cmp = Comparator.comparingInt(counter::get);

        k = elements.length - k;  // k-th largest
        quickSelect(elements, cmp, k);
        return Arrays.copyOfRange(elements, k, elements.length);
    }

    private static void quickSelect(int[] elements, Comparator<Integer> cmp, int k) {
        shuffle(elements);

        int lo = 0, hi = elements.length - 1;
        while (lo <= hi) {
            int j = partition(elements, cmp, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else /* if (j == k) */ return;
        }
    }

    private static int partition(int[] elements, Comparator<Integer> cmp, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // find items on left to swap
            while (i < hi && cmp.compare(elements[++i], elements[lo]) < 0) {
                if (i == hi) break;
            }
            // find items on right to swap
            while (j > lo && cmp.compare(elements[lo], elements[--j]) < 0) {
                if (j == lo) break;
            }
            // check if pointers cross swap
            if (i >= j) break;
            swap(elements, i, j);
        }
        // swap with partitioning item
        swap(elements, lo, j);
        // return index of item now known to be in place
        return j;
    }

    private static void swap(final int[] nums, final int i, final int j) {
        final int tmp = nums[i];
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
}
