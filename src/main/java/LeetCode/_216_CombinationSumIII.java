package LeetCode;

/*
https://leetcode.com/problems/combination-sum-iii/
Medium. Array, Backtracking.

Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination
should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _39_CombinationSum
 * @see _40_CombinationSumII
 * @see _377_CombinationSumIV
 */
class _216_CombinationSumIII {

    public static List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || n <= 0) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    private static void backtrack(final List<List<Integer>> res, final List<Integer> arr, final int k, int n, int start) {
        if (arr.size() == k && n == 0) {
            res.add(new ArrayList<>(arr));
        } else if (arr.size() < k && n > 0) {
            for (int i = start; i <= 9; i++) {
                arr.add(i);
                backtrack(res, arr, k, n - i, i + 1);
                arr.remove(arr.size() - 1);
            }
        }
    }
}
