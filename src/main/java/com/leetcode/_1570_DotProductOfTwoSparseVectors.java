package com.leetcode;

/*
https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
Medium. Array, Hash Table, Two Pointers, Design.

Given two sparse vectors, compute their dot product.

Implement class SparseVector:

 SparseVector(nums) Initializes the object with the vector nums
 dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values,
you should store the sparse vector efficiently and compute
the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:
 Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 Output: 8
 Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
  v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
 Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 Output: 0
 Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
  v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
 Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 Output: 6

Constraints:
 n == nums1.length == nums2.length
 1 <= n <= 10^5
 0 <= nums1[i], nums2[i] <= 100

Your SparseVector object will be instantiated and called as such:

 SparseVector v1 = new SparseVector(nums1);
 SparseVector v2 = new SparseVector(nums2);
 int ans = v1.dotProduct(v2);
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class _1570_DotProductOfTwoSparseVectors {

    static class SparseVector {
        private final Map<Integer, Integer> vector;

        /**
         * O(n), O(k)
         */
        SparseVector(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length > 1e5) throw new IllegalArgumentException();

            vector = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    vector.put(i, nums[i]);
                }
            }
        }

        /**
         * O(k1 * k2), O(1)
         *
         * @return the dotProduct of two sparse vectors
         */
        public int dotProduct(final SparseVector vec) {
            if (vec == null) return -1;

            final Set<Integer> s1 = this.vector.keySet();
            final Set<Integer> s2 = vec.vector.keySet();

            final Set<Integer> intersect = new HashSet<>(s1);
            intersect.retainAll(s2);

            int res = 0;
            for (int x : intersect) {
                res += this.vector.get(x) * vec.vector.get(x);
            }
            return res;
        }
    }

    static class SparseVector2 {
        private final List<int[]> vector;

        /**
         * O(n), O(k)
         */
        SparseVector2(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length > 1e5) throw new IllegalArgumentException();

            vector = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    vector.add(new int[]{i, nums[i]});
                }
            }
        }

        /**
         * O(min(k1, k2) * log(max(k1, k2))), O(1)
         *
         * @return the dotProduct of two sparse vectors
         */
        public int dotProduct(final SparseVector2 vec) {
            if (vec == null) return -1;
            else if (this.vector.size() > vec.vector.size()) return vec.dotProduct(this);

            int res = 0;
            for (int[] p : vector) {
                final int qIndex = binarySearch(vec.vector, p[0]);
                if (qIndex >= 0) {
                    res += (p[1] * vec.vector.get(qIndex)[1]);
                }
            }
            return res;
        }

        private static int binarySearch(final List<int[]> vector, final int target) {
            int lo = 0, hi = vector.size() - 1;
            while (lo <= hi) {
                final int mid = (lo + hi) >>> 1;
                if (vector.get(mid)[0] < target) {
                    lo = mid + 1;
                } else if (vector.get(mid)[0] > target) {
                    hi = mid - 1;
                } else if (vector.get(mid)[0] == target) {
                    return mid;
                }
            }
            return -1;
        }
    }
}
