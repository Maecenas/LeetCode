package com.leetcode;

/*
https://leetcode.com/problems/positions-of-large-groups/
Easy. Array.

In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.
We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting 3 and ending positions 6.

Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.

Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]

Note: 1 <= S.length <= 1000
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _830_PositionsOfLargeGroups {

    public static List<List<Integer>> largeGroupPositions(String S) {
        final List<List<Integer>> res = new ArrayList<>();
        if (S == null || S.length() < 3) return res;

        final char[] chars = S.toCharArray();
        int start = 0, end = 1;
        while (end < chars.length) {
            while (end < chars.length && chars[end] == chars[start]) {
                end++;
            }
            if (end - start >= 3) {
                res.add(Arrays.asList(start, end - 1));
            }
            start = end;
        }
        return res;
    }
}
