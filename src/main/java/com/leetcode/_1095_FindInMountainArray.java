package com.leetcode;

/*
https://leetcode.com/problems/find-in-mountain-array/
Hard.

(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
AND There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]

Given a mountain array mountainArr, return the minimum index such that
mountainArr.get(index) == target. If such an index doesn't exist, return -1.

You can't access the mountain array directly. You may only access the array
using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.

Submissions making more than 100 calls to MountainArray.get will be judged
Wrong Answer. Also, any solutions that attempt to circumvent the judge
will result in disqualification.

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.

Constraints:

3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9

// This is MountainArray's API interface.
// You should not implement it, or speculate about its implementation

interface MountainArray {
    public int get(int index) {}
    public int length() {}
}
*/

import java.util.HashMap;

interface MountainArray {
    int get(int index);

    int length();
}

/**
 * @see _162_FindPeakElement
 * @see _852_PeakIndexInAMountainArray
 */
class _1095_FindInMountainArray {

    public static int findInMountainArray(int target, MountainArray arr) {
        final int len = arr.length();
        if (len == 0) return -1;

        // cache of MountainArray.get()
        final HashMap<Integer, Integer> map = new HashMap<>();

        int lo = 0, hi = len - 1, mid;
        // find index of peak
        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (tryGet(arr, map, mid) < tryGet(arr, map, mid + 1)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        final int peak = lo;
        // find target in the left of peak
        lo = 0;
        hi = peak;
        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            if (tryGet(arr, map, mid) < target) {
                lo = mid + 1;
            } else if (tryGet(arr, map, mid) > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        // find target in the right of peak
        lo = peak;
        hi = len - 1;
        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            if (tryGet(arr, map, mid) > target) {
                lo = mid + 1;
            } else if (tryGet(arr, map, mid) < target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Caching of MountainArray.get()
     */
    private static int tryGet(MountainArray arr, HashMap<Integer, Integer> map, int key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            int val = arr.get(key);
            map.put(key, val);
            return val;
        }
    }
}

/**
 * Test stub
 */
class MyMountainArray implements MountainArray {

    private final int[] nums;

    MyMountainArray(int[] nums) {
        this.nums = nums;
    }

    @Override
    public int get(int index) {
        return nums[index];
    }

    @Override
    public int length() {
        return nums.length;
    }
}
