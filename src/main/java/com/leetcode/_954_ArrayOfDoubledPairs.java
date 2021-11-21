package com.leetcode;

/*
https://leetcode.com/problems/array-of-doubled-pairs/
Medium. Array, Hash Table.

Given an array of integers A with even length, return true if and only if
it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i]
for every 0 <= i < len(A) / 2.

Example 1:

Input: [3,1,3,6]
Output: false

Example 2:

Input: [2,1,2,6]
Output: false

Example 3:

Input: [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].

Example 4:

Input: [1,2,4,16,8,4]
Output: false

Note:

0 <= A.length <= 30000
A.length is even
-100000 <= A[i] <= 100000
*/

import java.util.TreeMap;

class _954_ArrayOfDoubledPairs {

    public static boolean canReorderDoubled(int[] A) {
        if (A == null || (A.length & 0x1) == 0x1) return false;

        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // early return
        if ((map.get(0) & 0x1) == 0x1) return false;

        for (int i : map.keySet()) {
            if (map.get(i) == 0) continue;
            // early return
            if (i < 0 && (i & 0x1) == 0x1) return false;
            // when i is negative, i / 2 == ((i + 1) >> 1)
            //int pair = i < 0 ? i / 2 : i * 2;
            int pair = i < 0 ? (i + 1) >> 1 : i << 1;
            int count = map.getOrDefault(pair, 0) - map.get(i);
            // early return
            if (count < 0) return false;
            map.put(pair, count);
        }
        return true;
    }
}
