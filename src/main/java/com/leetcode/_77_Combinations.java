package com.leetcode;

/*
Medium. Backtracking.

Given two integers n and k, return all possible combinations of k numbers out
of the range [1, n].

You may return the answer in any order.

Example 1:
 Input: n = 4, k = 2
 Output:
 [
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
 ]

Example 2:
 Input: n = 1, k = 1
 Output: [[1]]

Constraints:
 1 <= n <= 20
 1 <= k <= n
*/

import java.util.ArrayList;
import java.util.List;

class _77_Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        if (k < 0 || k > n || n > 20) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int n, final int k, final int start) {
        if (arr.size() == k) {
            res.add(new ArrayList<>(arr));
        } else if (arr.size() < k) {
            for (int i = start; i <= n; i++) {
                arr.add(i);
                backtrack(res, arr, n, k, i + 1);
                arr.remove(arr.size() - 1);
            }
        }
    }
}
