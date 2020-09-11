package LeetCode;

/*
https://leetcode.com/problems/merge-sorted-array/
Easy. Array, Two Pointers.

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n)
to hold additional elements from nums2.

Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

Constraints:

-10^9 <= nums1[i], nums2[i] <= 10^9
nums1.length == m + n
nums2.length == n
*/

class _88_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length != m + n || nums2 == null || nums2.length != n) return;

        // merge from tail to head, copy each element only once
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            // Such is related to language or compiler's interpretation of
            // sequence of increment (i++, ++i) or decrement (i--, --i) operators
            // nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
        // i == 0 and copy rest of nums[2] to nums[1]
        while (j >= 0) {
            // nums1[k--] = nums2[j--];
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }
}
