package com.leetcode;

/*
https://leetcode.com/problems/longest-palindromic-subsequence/
Medium. String, Dynamic Programming.

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another
sequence by deleting some or no elements without changing
the order of the remaining elements.

Example 1:
 Input: s = "bbbab"
 Output: 4
 Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
 Input: s = "cbbd"
 Output: 2
 Explanation: One possible longest palindromic subsequence is "bb".

Constraints:
 1 <= s.length <= 1000
 s consists only of lowercase English letters.
*/

/**
 * @see _1216_ValidPalindromeIII
 * @see _1312_MinimumInsertionStepsToMakeAStringPalindrome
 */
class _516_LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0 || s.length() > 1_000) return -1;

        // dp[i][j] - length of the longest palindrome subsequence in s[i:j]
        final int n = s.length();
        final int[][] dp = new int[2][n];

        final char[] chars = s.toCharArray();
        // base case
        for (int i = 0; i < n; i++) {
            dp[i % 2][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) dp[i % 2][j] = 2 + dp[(i + 1) % 2][j - 1];
                else dp[i % 2][j] = Math.max(dp[(i + 1) % 2][j], dp[i % 2][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0 || s.length() > 1_000) return -1;

        // dp[i][j] - minimum cost to create a palindrome in s[i:j]
        final int[][] dp = new int[2][s.length()];

        final char[] chars = s.toCharArray();
        // gradually grow i and j
        for (int i = chars.length - 2; i >= 0; i--)
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                // delete s[i] or s[j]
                else dp[i % 2][j] = 1 + Math.min(dp[(i + 1) % 2][j], dp[i % 2][j - 1]);
            }
        return s.length() - dp[0][s.length() - 1];
    }
}
