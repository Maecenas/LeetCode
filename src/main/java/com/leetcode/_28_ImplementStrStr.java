package com.leetcode;

/*
https://leetcode.com/problems/implement-strstr/
Easy. Two Pointers, String.

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1


Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string.
This is consistent to C's strstr() and Java's indexOf().
*/

/**
 * Substring Search can be made in linear/ sub-linear time with algorithms like
 * Knuth-Morris-Pratt, Boyer-Moore and Rabin-Karp. See also:
 * <a>https://algs4.cs.princeton.edu/53substring/</a>
 * <p>
 * Such type of questions can be extended as Pattern Matching
 */
class _28_ImplementStrStr {

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.equals("")) return 0;

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            // no exit condition means something to dealt with on end
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
        return -1;
    }
}
