package LeetCode;

/*
https://leetcode.com/problems/intersection-of-two-arrays/
Easy. Hash Table, Two Pointers, Binary Search, Sort.

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:

Each element in the result must be unique.
The result can be in any order.
*/

import java.util.ArrayList;
import java.util.HashSet;

class _349_IntersectionOfTwoArrays {

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        else if (nums1.length == 0 || nums2.length == 0) return new int[0];

        // make sure nums1 is smaller
        if (nums2.length < nums1.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        final HashSet<Integer> set = new HashSet<>(nums1.length);
        final ArrayList<Integer> arr = new ArrayList<>();
        // Add all elements to set from array 1
        for (int num : nums1) {
            set.add(num);
        }
        // If an element in array 1 also present in array 2
        // then add to res and remove from set
        for (int num : nums2) {
            if (set.contains(num)) {
                arr.add(num);
                set.remove(num);
            }
        }
        // Convert List to array
        final int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }
}
