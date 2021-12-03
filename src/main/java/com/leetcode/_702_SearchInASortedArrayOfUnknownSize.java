package com.leetcode;

/*
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
Median. Array, Binary Search, Interactive.

This is an interactive problem.

You have a sorted array of unique elements and an unknown size. You do not have
an access to the array but you can use the ArrayReader interface to access it.
You can call ArrayReader.get(i) that:

 returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 returns 2^31 - 1 if the i is out of the boundary of the array.

You are also given an integer target.

Return the index k of the hidden array where secret[k] == target or return -1 otherwise.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
 Input: secret = [-1,0,3,5,9,12], target = 9
 Output: 4
 Explanation: 9 exists in secret and its index is 4.

Example 2:
 Input: secret = [-1,0,3,5,9,12], target = 2
 Output: -1
 Explanation: 2 does not exist in secret so return -1.

Constraints:
 1 <= secret.length <= 10^4
 -10^4 <= secret[i], target <= 10^4
 secret is sorted in a strictly increasing order.

// This is ArrayReader's API interface.
// You should not implement it, or speculate about its implementation
interface ArrayReader {
    public int get(int index) {}
}
*/

/**
 * @see _34_FindFirstAndLastPositionOfElementInSortedArray
 * @see _704_BinarySearch
 */
class _702_SearchInASortedArrayOfUnknownSize {

    interface ArrayReader {
        int get(int index);
    }

    public static int search(ArrayReader reader, int target) {
        if (reader == null || reader.get(0) == Integer.MAX_VALUE || target < -1e4 || target > 1e4) return -1;
        if (reader.get(0) == target) return 0;

        int hi = 1, lo, mid;
        while (reader.get(hi) < target) {
            hi <<= 1;
        }
        if (reader.get(hi) == target) return hi;

        lo = hi >> 1;
        // search in [lo, hi)
        while (lo < hi) {
            mid = (lo + hi) >>> 1;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                lo = mid + 1;
            } else if (reader.get(mid) > target) {
                hi = mid;
            }
        }
        if (reader.get(lo) != target) return -1;
        return lo;
    }
}
