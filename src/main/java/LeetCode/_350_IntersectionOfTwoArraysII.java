package LeetCode;

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/
Easy. Hash Table, Two Pointers, Binary Search, Sort.

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?

What if nums1's size is small compared to nums2's size? Which algorithm is better?

What if elements of nums2 are stored on disk, and the memory is limited such that
you cannot load all elements into the memory at onc e?
*/

import java.util.ArrayList;
import java.util.HashMap;

class _350_IntersectionOfTwoArraysII {

    /**
     * O(m + n), O(min(m, n))
     * Follow up: nums1's size is small compared to nums2's size
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        else if (nums1.length == 0 || nums2.length == 0) return new int[0];

        final ArrayList<Integer> arr = new ArrayList<>();
        final HashMap<Integer, Integer> map = new HashMap<>();

        // make sure nums1 is smaller
        if (nums2.length > nums1.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        for (int num : nums1) {
            //if (map.containsValue(num)) {
            //    map.put(num, map.get(num) + 1);
            //} else {
            //    map.put(num, 0);
            //}
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                arr.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        // Convert List to array
        final int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    /**
     * O(m + n), O(min(m, n))
     * Follow up: the given array is already sorted
     * Follow up: elements of nums2 are stored on disk,
     *            cannot load all elements into the memory at once
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        else if (nums1.length == 0 || nums2.length == 0) return new int[0];

        //assert isSorted(nums1);
        assert isSorted(nums2);

        final ArrayList<Integer> arr = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] < nums2[i2]) {
                i1++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                // nums1[i] == nums2[j]
                arr.add(nums1[i1]);
                i1++;
                i2++;
            }
        }
        // Convert List to array
        final int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    private static boolean isSorted(int[] nums) {
        if (nums == null) return false;
        else if (nums.length == 0) return true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
