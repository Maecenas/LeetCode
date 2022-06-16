package com.leetcode;

/*
https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
Hard. String, Dynamic Programming.

Given a string s. In one step you can insert
any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

Example 1:
 Input: s = "zzazz"
 Output: 0
 Explanation: The string "zzazz" is already palindrome we don't need any insertions.

Example 2:
 Input: s = "mbadm"
 Output: 2
 Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:
 Input: s = "leetcode"
 Output: 5
 Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Constraints:
 1 <= s.length <= 500
 s consists of lowercase English letters.
*/

/**
 * @see _516_LongestPalindromicSubsequence
 * @see _1216_ValidPalindromeIII
 */
class _1312_MinimumInsertionStepsToMakeAStringPalindrome {

    public static int minInsertions(String s) {
        if (s == null || s.length() == 0 || s.length() > 500) return -1;

        final char[] chars = s.toCharArray();
        final int n = s.length();
        // dp[i][j] - min insertions to make longest palindrome subsequence in s[i:j]
        final int[][] dp = new int[2][n];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                else dp[i % 2][j] = 1 + Math.min(dp[(i + 1) % 2][j], dp[(i) % 2][j - 1]);
            }
        }

        return dp[0][n - 1];
    }
}
