package com.leetcode;

/*
https://leetcode.com/problems/advantage-shuffle/
Medium. Array, Greedy.

Given two arrays A and B of equal size, the advantage of A with respect to B
is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]

Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

class _870_AdvantageShuffle {

    /**
     * O(nlogn), O(n)
     * Using Tian Ji Horse-racing strategy
     * <p>
     * Other solutions without sorting B will fail if there are duplicate elements in B,
     * because we're using elements as keys and a hashtable can not have duplicate
     * keys with different values
     */
    public static int[] advantageCount(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return new int[0];

        final int len = A.length;

        final int[] sortedA = Arrays.copyOf(A, len), sortedB = Arrays.copyOf(B, len);
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);

        // assigned[b] = A's elements that are assigned to beat b
        final HashMap<Integer, Deque<Integer>> assigned = new HashMap<>();
        for (int b : B) {
            assigned.putIfAbsent(b, new ArrayDeque<>());
        }

        // remaining = A's element that are not assigned to any b
        final Deque<Integer> remained = new ArrayDeque<>();

        // populate (assigned, remaining) appropriately
        // sortedB[j] is always the smallest unassigned element in B
        int j = 0;
        for (int a : sortedA) {
            if (a > sortedB[j]) {
                assigned.get(sortedB[j]).offer(a);
                j++;
            } else {
                remained.offer(a);
            }
        }

        final int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            // if there is some a assigned to b
            Integer x = assigned.get(B[i]).poll();
            if (x == null) x = remained.poll();
            // assert x != null;
            res[i] = x;
        }
        return res;
    }

    /**
     * Virtually sort B by an array to record the index of B after sorting
     */
    public static int[] advantageCount2(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return new int[0];

        Arrays.sort(A);

        final int len = A.length;
        final Integer[] bIndex = new Integer[len];
        for (int i = 0; i < len; i++) {
            bIndex[i] = i;
        }
        Arrays.sort(bIndex, Comparator.comparingInt(i -> B[i]));

        final int[] res = new int[len];
        int start = 0, end = len - 1;
        for (int idxA = 0, idxB = 0; idxA < len; idxA++) {
            if (A[idxA] > B[bIndex[idxB]]) {
                res[bIndex[start]] = A[idxA];
                idxB++;
                start++;
            } else {
                res[bIndex[end]] = A[idxA];
                end--;
            }
        }
        return res;
    }

    /**
     * Using PriorityQueue
     */
    public static int[] advantageCount3(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return new int[0];

        Arrays.sort(A);

        final int len = A.length;
        // added by decreasing order
        final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < len; i++) {
            pq.add(new int[]{B[i], i});
        }

        final int[] res = new int[len];
        int lo = 0, hi = len - 1;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int val = arr[0];
            int idx = arr[1];
            if (A[hi] <= val) {
                res[idx] = A[lo];
                lo++;
            } else {
                res[idx] = A[hi];
                hi--;
            }
        }
        return res;
    }

    /**
     * Using BinarySearch
     */
    public static int[] advantageCount4(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return new int[0];

        Arrays.sort(A);

        final int len = A.length;
        final int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            res[i] = binarySearch(A, B[i]);
        }
        return res;
    }

    /**
     * Return the smallest unused index i in A that A[i] > target,
     * the smallest unused index i from 0 otherwise
     * <p>
     * Marked A[i] as visited by flip all the bits (~) of A[i]
     */
    private static int binarySearch(final int[] A, final int target) {
        final int len = A.length;
        int lo = 0, hi = len - 1;

        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);

            if ((A[mid] >= 0 ? A[mid] : ~A[mid]) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (A[lo] > target) {
            while (lo < len && A[lo] < 0) lo++;
            if (lo < len) {
                A[lo] = ~A[lo];
                return ~A[lo];
            }
        }

        lo = 0;
        while (lo < len && A[lo] < 0) lo++;
        A[lo] = ~A[lo];
        return ~A[lo];
    }

    /**
     * Using TreeMap
     */
    public static int[] advantageCount5(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return new int[0];

        final int len = A.length;
        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        final int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            Integer x = map.higherKey(B[i]);
            if (x == null) x = map.firstKey();

            if (map.get(x) == 1) map.remove(x);
            else map.put(x, map.get(x) - 1);
            res[i] = x;
        }
        return res;
    }
}
