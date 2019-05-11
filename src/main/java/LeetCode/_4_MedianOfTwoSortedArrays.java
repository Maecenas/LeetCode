package LeetCode;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/
Hard. Array, Binary Search, Divide and Conquer.

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
*/

class _4_MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0.0;

        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            // to ensure n >= m
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        // Binary Search for i in [0, m] that satisfies:
        // B[j − 1] <= A[i] and A[i − 1] <= B[j],
        // where j = (m + n + 1) / 2 - i;

        int lo = 0, hi = m, i, j;  // hi != m - 1
        final int halfLen = (m + n + 1) >> 1;

        while (lo <= hi) {
            i = lo + ((hi - lo) >> 1);
            j = halfLen - i;
            if (i < hi && nums2[j - 1] > nums1[i]) {
                lo = i + 1;
            } else if (i > lo && nums1[i - 1] > nums2[j]) {
                hi = i - 1;
            } else {
                // The median is:
                // when m + n is odd, max(A[i − 1], B[j − 1])
                // when m + n is even, (max(A[i − 1], B[j − 1]) + min(A[i], B[j])) / 2

                // max(A[i − 1], B[j − 1])
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // (m + n) is odd
                if (((m + n) & 0x1) == 1) {
                    return maxLeft;
                }

                // min(A[i], B[j])
                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                // (m + n) is even
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
