package com.leetcode;

/*
https://leetcode.com/problems/first-unique-character-in-a-string/
Easy. Hash Table, String.

Given a string, find the first non-repeating character in it and return it's index.
If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters.
*/

class _387_FirstUniqueCharacterInAString {

    private static final int R = 26;  // lowercase letters

    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;

        final char[] chars = s.toCharArray();
        final int[] count = new int[R];
        for (char c : chars) {
            count[c - 'a'] = count[c - 'a'] + 1;
        }

        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) return i;
        }
        return -1;
    }
}
