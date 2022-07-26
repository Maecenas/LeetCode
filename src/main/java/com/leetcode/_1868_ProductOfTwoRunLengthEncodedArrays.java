package com.leetcode;

/*
https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
Medium. Array, Two Pointers.

Run-length encoding is a compression algorithm that allows for an integer
array nums with many segments of consecutive repeated numbers to be represented by
a (generally smaller) 2D array encoded. Each encoded[i] = [val_i, freq_i]
describes the i-th segment of repeated numbers in nums where val_i is the value that is
repeated freq_i times.

For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length
encoded array encoded = [[1,3],[2,5]]. Another way to read this is "three 1's
followed by five 2's".

The product of two run-length encoded arrays encoded1 and encoded2 can be
calculated using the following steps:

 Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
 Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
 Compress prodNums into a run-length encoded array and return it.

You are given two run-length encoded arrays encoded1 and encoded2
representing full arrays nums1 and nums2 respectively. Both nums1 and nums2 have the same
length. Each encoded1[i] = [val_i, freq_i] describes the i-th segment of nums1, and
each encoded2[j] = [val_j, freq_j] describes the j-th segment of nums2.

Return the product of encoded1 and encoded2.

Note: Compression should be done such that the run-length encoded array has
the minimum possible length.

Example 1:
 Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
 Output: [[6,6]]
 Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded
array [[6,6]].

Example 2:
 Input: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
 Output: [[2,3],[6,1],[9,2]]
 Explanation: encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
   prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].

Constraints:
 1 <= encoded1.length, encoded2.length <= 10^5
 encoded1[i].length == 2
 encoded2[j].length == 2
 1 <= val_i, freq_i <= 10⁴ for each encoded1[i].
 1 <= val_j, freq_j <= 10⁴ for each encoded2[j].
 The full arrays that encoded1 and encoded2 represent are the same length.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _1868_ProductOfTwoRunLengthEncodedArrays {

    public static List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        if (encoded1 == null || encoded2 == null || encoded1.length != encoded2.length
            || encoded1.length == 0 || encoded1.length > 1e5) return new ArrayList<>();

        int p1 = 0, p2 = 0;
        final List<List<Integer>> res = new ArrayList<>();

        while (p1 < encoded1.length) {
            final int len = Math.min(encoded1[p1][1], encoded2[p2][1]);
            final int product = encoded1[p1][0] * encoded2[p2][0];

            // update the previous product when product is the same
            if (res.size() > 0 && res.get(res.size() - 1).get(0) == product) {
                res.get(res.size() - 1).set(1, res.get(res.size() - 1).get(1) + len);
            } else {
                res.add(Arrays.asList(product, len));
            }

            encoded1[p1][1] -= len;
            encoded2[p2][1] -= len;
            if (encoded1[p1][1] == 0) p1++;
            if (encoded2[p2][1] == 0) p2++;
        }
        return res;
    }
}
