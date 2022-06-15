package com.leetcode;

/*
https://leetcode.com/problems/k-closest-points-to-origin/
Medium. Array, Math, Divide and Conquer, Geometry, Sorting, Heap, Quickselect.

Given an array of points where points[i] = [xi, yi] represents a point on the
X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance
(i.e., sqrt((x1 - x2)^2 + (y1 - y2)^2).

You may return the answer in any order.
The answer is guaranteed to be unique (except for the order that it is in).

Example 1:
 Input: points = [[1,3],[-2,2]], k = 1
 Output: [[-2,2]]
 Explanation:
  The distance between (1, 3) and the origin is sqrt(10).
  The distance between (-2, 2) and the origin is sqrt(8).
  Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
  We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
 Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 Output: [[3,3],[-2,4]]
 Explanation: The answer [[-2,4],[3,3]] would also be accepted.

Constraints:
 1 <= k <= points.length <= 10^4
 -10^4 < xi, yi < 10^4
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @see _215_KthLargestElementInAnArray#findKthLargest3
 * @see _347_TopKFrequentElements#topKFrequent3
 */
class _973_KClosestPointsToOrigin {

    /**
     * O(nlogk), O(k)
     * Heap
     */
    public static int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length > 1e4 || k < 1 || k > points.length) return new int[0][];

        // maxHeap
        final PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(point -> -(point[0] * point[0] + point[1] * point[1])));
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        final int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    /**
     * O(n) / O(n^2) worst, O(1)
     * Quick Select
     */
    public static int[][] kClosest2(int[][] points, int k) {
        if (points == null || points.length > 1e4 || k < 1 || k > points.length) return new int[0][];

        quickSelect(points, k);
        return Arrays.copyOf(points, k);
    }

    private static void quickSelect(int[][] points, int k) {
        shuffle(points);

        int lo = 0, hi = points.length - 1;
        while (lo < hi) {
            int j = partition(points, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else /* if (j == k) */ return;
        }
    }

    private static int partition(int[][] points, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // find items on left to swap
            while (i < hi && distance(points[++i]) < distance(points[lo])) {
                if (i == hi) break;
            }
            // find items on right to swap
            while (j > lo && distance(points[lo]) < distance(points[--j])) {
                if (j == lo) break;
            }
            // check if pointers cross swap
            if (i >= j) break;
            swap(points, i, j);
        }
        // swap with partitioning item
        swap(points, lo, j);
        // return index of item now known to be in place
        return j;
    }

    private static int distance(final int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private static void swap(final int[][] points, final int i, final int j) {
        final int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    /**
     * Knuth Shuffle
     */
    private static void shuffle(int[][] points) {
        final ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 1; i < points.length; i++) {
            int r = rand.nextInt(i + 1);
            swap(points, i, r);
        }
    }
}
