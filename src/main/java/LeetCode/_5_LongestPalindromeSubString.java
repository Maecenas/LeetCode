package LeetCode;

/*
https://leetcode.com/problems/longest-palindromic-substring/
Medium. String, Dynamic Programming.

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"

*/

class _5_LongestPalindromeSubString {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        // Copy String to char[] for better performance
        final char[] chars = s.toCharArray();
        int start = 0, end = 0;
        int len;  // tmp

        for (int i = 0; i < s.length(); i++) {
            len = Math.max(
                    expandAroundCenter(chars, i, i),
                    expandAroundCenter(chars, i, i + 1));
            if (len > end - start) {
                // “shift” start towards right
                // as each center is right leaned (i OR i + 0.5)
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // len = end - start
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(final char[] chars, final int left, final int right) {
        int L = left, R = right;
        while (L >= 0 && R < chars.length && chars[L] == chars[R]) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
