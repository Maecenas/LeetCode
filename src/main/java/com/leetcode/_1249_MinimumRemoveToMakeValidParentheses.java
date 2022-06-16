package com.leetcode;

/*
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
Medium. String, Stack.

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any
positions ) so that the resulting parentheses string is valid
and return any valid string.

Formally, a parentheses string is valid if and only if:

 It is the empty string, contains only lowercase characters, or
 It can be written as AB (A concatenated with B),
   where A and B are valid strings, or
 It can be written as (A), where A is a valid string.

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

Constraints:

1 <= s.length <= 10^5
s[i] is one of '(' , ')' and lowercase English letters.
*/

class _1249_MinimumRemoveToMakeValidParentheses {

    public static String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0 || s.length() > 1e5) return "";

        // remove all invalid ")"
        final StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // remove excessive "("
        for (int i = sb.length() - 1; i >= 0 && balance > 0; i--) {
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                balance--;
            }
        }

        return sb.toString();
    }
}
