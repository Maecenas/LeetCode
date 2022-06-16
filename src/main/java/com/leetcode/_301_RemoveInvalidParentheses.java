package com.leetcode;

/*
https://leetcode.com/problems/remove-invalid-parentheses/
Hard. String, Backtracking, Breadth-First Search.

Given a string s that contains parentheses and letters, remove the minimum
number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.

Example 1:
 Input: s = "()())()"
 Output: ["(())()","()()()"]

Example 2:
 Input: s = "(a)())()"
 Output: ["(a())()","(a)()()"]

Example 3:
 Input: s = ")("
 Output: [""]

Constraints:
 1 <= s.length <= 25
 s consists of lowercase English letters and parentheses '(' and ')'.
 There will be at most 20 parentheses in s.
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _1249_MinimumRemoveToMakeValidParentheses
 */
class _301_RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0 || s.length() > 25) return new ArrayList<>();

        final List<String> res = new ArrayList<>();
        backtracking(s, 0, 0, '(', ')', res);
        return res;
    }

    private static void backtracking(String s, int iStart, int jStart, char openChar, char closeChar, List<String> res) {
        int opens = 0, closes = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openChar) opens++;
            if (s.charAt(i) == closeChar) closes++;
            if (closes > opens) {
                // try removing each closing parentheses, skipping duplicates
                for (int j = jStart; j <= i; j++) {
                    if (s.charAt(j) == closeChar && (j == jStart || s.charAt(j - 1) != closeChar)) {
                        // store jStart prevents duplicates
                        backtracking(s.substring(0, j) + s.substring(j + 1), i, j, openChar, closeChar, res);
                    }
                }
                return;
            }
        }
        // finished from left to right
        final String reversed = new StringBuilder(s).reverse().toString();
        if (openChar == '(') {
            backtracking(reversed, 0, 0, ')', '(', res);
        } else /* if (openChar == ')') */ {
            res.add(reversed);
        }
    }
}
