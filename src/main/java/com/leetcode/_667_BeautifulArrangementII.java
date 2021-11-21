package com.leetcode;

/*
https://leetcode.com/problems/beautiful-arrangement-ii/
Medium. Array.

Given two integers n and k, you need to construct a list which contains n
different positive integers ranging from 1 to n and obeys the following requirement:

Suppose this list is [a_1, a_2, a_3, ... , a_n], then the list
[ |a_1 - a_2|, |a_2 - a_3|, |a_3 - a_4|, ... , |a_n-1 - a_n| ]
has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:

Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3,
and the [1, 1] has exactly 1 distinct integer: 1.

Example 2:

Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3,
and the [2, 1] has exactly 2 distinct integers: 1 and 2.

Note:

The n and k are in the range 1 <= k < n <= 10^4.
*/

/**
 * @see _526_BeautifulArrangement
 */
class _667_BeautifulArrangementII {

    /**
     * O(n), O(n)
     *
     * @see <a href="https://leetcode.com/articles/beautiful-arrangement-ii/">explaination</a>
     */
    public static int[] constructArray(int n, int k) {
        if (k < 1 || k >= n) return new int[0];

        // say the list in sorted order [1, 2, ..., n]
        final int[] res = new int[n];
        int idx = 0;
        // pad array with [1, ..., n - k]
        // initialize the array with length (n - k + 1) would fail k == 2
        // as distinct distance may then start from k - 1 == 1
        for (int i = 1; i <= n - k; i++) {
            res[idx] = i;
            idx++;
        }
        // recursively choose from the tail and head of the rest k value
        // [n - k + 1, ..., n]
        // making up for (k - 1) distinct value
        for (int i = 1; i <= k; i++) {
            if (((i & 0x1) == 0x0)) {
                res[idx] = n - k + (i >> 1);
            } else {
                res[idx] = n - (i >> 1);
            }
            idx++;
        }
        return res;
    }
}
