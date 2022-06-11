package com.leetcode;

/*
https://leetcode.com/problems/reverse-string/
Easy. Two Pointers, String, Recursion.

Write a function that reverses a string.
The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
 Input: s = ["h","e","l","l","o"]
 Output: ["o","l","l","e","h"]

Example 2:
 Input: s = ["H","a","n","n","a","h"]
 Output: ["h","a","n","n","a","H"]

Constraints:
 1 <= s.length <= 10^5
 s[i] is a printable ascii character.
*/

class _344_ReverseString {

    public static void reverseString(char[] s) {
        if (s == null || s.length == 0 || s.length > 1e5) return;

        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }
}
