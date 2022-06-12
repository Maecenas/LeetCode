package com.leetcode;

/*
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
Medium. String, Stack, Greedy.

A parentheses string is valid if and only if:

 It is the empty string,
 It can be written as AB (A concatenated with B), where A and B are valid strings, or
 It can be written as (A), where A is a valid string.

You are given a parentheses string s.
In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))"
or a closing parenthesis to be "())))".

Return the minimum number of moves required to make s valid.

Example 1:
 Input: s = "())"
 Output: 1

Example 2:
 Input: s = "((("
 Output: 3

Constraints:
 1 <= s.length <= 1000
 s[i] is either '(' or ')'.
*/

class _921_MinimumAddToMakeParenthesesValid {

    public static int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0 || s.length() > 1_000) return -1;

        int res = 0;
        int count = 0;
        // count '(' for 1 and ')' for -1
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (count >= 0) count++;
                else {
                    res -= count;
                    count = 1;
                }
            } else if (ch == ')') {
                count--;
            }
        }
        return res + Math.abs(count);
    }
}
