package com.leetcode;

/*
https://leetcode.com/problems/valid-palindrome-iii/
Hard. String, Dynamic Programming.

Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by
removing at most k characters from it.

Example 1:
 Input: s = "abcdeca", k = 2
 Output: true
 Explanation: Remove 'b' and 'e' characters.

Example 2:
 Input: s = "abbababa", k = 1
 Output: true

Constraints:
 1 <= s.length <= 1000
 s consists of only lowercase English letters.
 1 <= k <= s.length
*/

import java.util.Arrays;

/**
 * @see _125_ValidPalindrome
 * @see _516_LongestPalindromicSubsequence
 * @see _680_ValidPalindromeII
 */
class _1216_ValidPalindromeIII {

    /**
     * O(n^2), O(n^2)
     * 2D DP Top Down
     */
    public static boolean isValidPalindromeDPTopDown(String s, int k) {
        if (s == null || s.length() > 1_000 || k < 1 || k > s.length()) return false;

        final int[][] dp = new int[s.length()][s.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return isValidPalindromeDPTopDown(s.toCharArray(), 0, s.length() - 1, dp) <= k;
    }

    /**
     * @return the minimum deletion cost to get a palindromic subsequence in s[i:j]
     */
    private static int isValidPalindromeDPTopDown(final char[] s, final int i, final int j, final int[][] dp) {
        if (i >= j) return 0;
        else if (i == j - 1) return (s[i] == s[j] ? 0 : 1);

        if (dp[i][j] != -1) return dp[i][j];
        if (s[i] == s[j]) return (dp[i][j] = isValidPalindromeDPTopDown(s, i + 1, j - 1, dp));
        // delete s[i] or s[j]
        else return (dp[i][j] = 1 + Math.min(
                isValidPalindromeDPTopDown(s, i + 1, j, dp),
                isValidPalindromeDPTopDown(s, i, j - 1, dp)
        ));
    }

    /**
     * O(n^2), O(n^2)
     * 2D DP Bottom Up
     */
    public static boolean isValidPalindromeDPBottomUp(String s, int k) {
        if (s == null || s.length() > 1_000 || k < 1 || k > s.length()) return false;

        // dp[i][j] - minimum cost to create a palindrome in s[i:j]
        final int[][] dp = new int[s.length()][s.length()];

        final char[] chars = s.toCharArray();
        // gradually grow i and j
        for (int i = chars.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) dp[i][j] = dp[i + 1][j - 1];
                    // delete s[i] or s[j]
                else dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][s.length() - 1] <= k;
    }

    /**
     * O(n^2), O(n)
     * 1D DP Bottom Up
     */
    public static boolean isValidPalindrome(String s, int k) {
        if (s == null || s.length() > 1_000 || k < 1 || k > s.length()) return false;

        // dp[i][j] - minimum cost to create a palindrome in s[i:j]
        final int[][] dp = new int[2][s.length()];

        final char[] chars = s.toCharArray();
        // gradually grow i and j
        for (int i = chars.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                    // delete s[i] or s[j]
                else dp[i % 2][j] = 1 + Math.min(dp[(i + 1) % 2][j], dp[i % 2][j - 1]);
            }
        }
        return dp[0][s.length() - 1] <= k;
    }
}
