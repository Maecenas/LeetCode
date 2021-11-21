package com.leetcode;

/*
https://leetcode.com/problems/isomorphic-strings/
Easy. Hash Table.

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character
while preserving the order of characters. No two characters may map to
the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true

Example 2:

Input: s = "foo", t = "bar"
Output: false

Example 3:

Input: s = "paper", t = "title"
Output: true

Note:
You may assume both s and t have the same length.
*/

/**
 * @see _290_WordPattern
 */
class _205_IsomorphicStrings {

    private static final int R = 256;  // extended ASCII

    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        final int[] lastSeenAt = new int[2 * R];

        // each char pairs in s and t must have distance of R
        for (int i = 0; i < s.length(); i++) {
            if (lastSeenAt[s.charAt(i)] != lastSeenAt[t.charAt(i) + R]) return false;
            lastSeenAt[s.charAt(i)] = lastSeenAt[t.charAt(i) + R] = i + 1;
        }
        return true;
    }
}
