package com.leetcode;

/*
https://leetcode.com/problems/valid-palindrome/
Easy. Two Pointers, String.

Given a string, determine if it is a palindrome, considering only alphanumeric
characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid
palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:

Input: "race a car"
Output: false

Constraints:

s consists only of printable ASCII characters.
*/

/**
 * @see _680_ValidPalindromeII
 * @see _1216_ValidPalindromeIII
 */
class _125_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;

        final char[] chars = s.toLowerCase().toCharArray();

        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(chars[i])) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(chars[j])) {
                j--;
            }

            if (chars[i] != chars[j]) return false;
        }

        return true;
    }
}
