package LeetCode;

/*
https://leetcode.com/problems/regular-expression-matching/
Hard. String, Dynamic Programming, Backtracking.

Given an input string (s) and a pattern (p), implement regular expression matching
with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.


The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'.
Therefore, by repeating 'a' once, it becomes "aa".

Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time.
Therefore it matches "aab".

Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
*/

/**
 * With '.' and '*' Metacharacters
 */
class _10_RegularExpressionMatching {

    /**
     * dp(i, j) = is text[i:] and pattern[j:] matches
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        final boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean isMatchFirst = i < s.length()
                        && (p.charAt(j) == s.charAt(i)
                        || p.charAt(j) == '.');
                if (j + 2 <= p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (isMatchFirst && dp[i + 1][j]);
                } else {
                    dp[i][j] = isMatchFirst && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}