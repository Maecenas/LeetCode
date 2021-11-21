package com.leetcode;

/*
https://leetcode.com/problems/longest-palindrome/
Easy. Hash Table.

Given a string which consists of lowercase or uppercase letters,
find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/

import java.util.HashSet;

class _409_LongestPalindrome {

    private static final int R = 26;  // lowercase/ uppercase letters

    public static int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;

        HashSet<Character> set = new HashSet<>();
        int count = 0;  // number of pairs
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count++;
            } else {
                set.add(c);
            }
        }

        if (!set.isEmpty()) return (count << 1) + 1;
        return (count << 1);
    }

    public static int longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return 0;

        final int[] count = new int[2 * R];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count[c - 'a']++;
            } else {
                //count[c - 'A' + 26]++;
                count[c - 39]++;
            }
        }
        int res = 0;
        final int mask = 0xFFFFFFFE;
        for (int pairs : count) {
            //res += (pairs / 2 * 2);
            //res += (pairs >> 1 << 1);
            res += (pairs & mask);
        }
        if (res != s.length()) return res + 1;
        return res;
    }
}
