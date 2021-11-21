package com.leetcode;

/*
https://leetcode.com/problems/valid-anagram/
Easy. Hash Table, Sort.

Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true

Example 2:

Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters?
How would you adapt your solution to such case?
*/

class _242_ValidAnagram {

    private static final int R = 26;  // lowercase alphabets

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        final int[] counter = new int[R];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            counter[t.charAt(i) - 'a']--;
            if (counter[t.charAt(i) - 'a'] < 0) return false;
        }
        for (int count : counter) {
            if (count != 0) return false;
        }
        return true;
    }
}
