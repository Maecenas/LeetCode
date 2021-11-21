package com.leetcode;

/*
https://leetcode.com/problems/valid-palindrome-ii/
Easy. String.

Given a non-empty string s, you may delete at most one character.
Judge whether you can make it a palindrome.

Example 1:

Input: "aba"
Output: True

Example 2:

Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:

The string will only contain lowercase characters a-z.
The maximum length of the string is 50000.
*/

/**
 * @see _125_ValidPalindrome
 */
class _680_ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;

        final char[] chars = s.toCharArray();
        final int len = s.length();

        for (int i = 0; i < (len >> 1); i++) {
            if (chars[i] != chars[len - 1 - i]) {
                int j = s.length() - 1 - i;
                return validPalindrome(chars, i + 1, j)
                        || validPalindrome(chars, i, j - 1);
            }
        }
        return true;
    }

    private static boolean validPalindrome(char[] str, int i, int j) {
        for (int k = i; k <= (i + j) >>> 1; k++) {
            if (str[k] != str[j - k + i]) return false;
        }
        return true;
    }
}
