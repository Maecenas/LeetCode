package com.leetcode;

/*
https://leetcode.com/problems/generate-parentheses/
Medium. String, Backtracking.

Given n pairs of parentheses, write a function to generate
all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

import java.util.ArrayList;
import java.util.List;

class _22_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        final List<String> res = new ArrayList<>();
        if (n <= 0) return res;

        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private static void backtrack(final List<String> strings, final StringBuilder sb, int open, int close, final int max) {
        if (sb.length() == max * 2) {
            strings.add(sb.toString());
            return;
        }

        if (open < max) {
            backtrack(strings, sb.append("("), open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            backtrack(strings, sb.append(")"), open, close + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
