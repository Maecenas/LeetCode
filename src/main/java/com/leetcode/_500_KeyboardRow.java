package com.leetcode;

/*
https://leetcode.com/problems/keyboard-row/
Easy. Hash Table.

Given a List of words, return the words that can be typed using letters of alphabet
on only one row's of American keyboard like the image below.

Example:

Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
*/

import java.util.ArrayList;
import java.util.regex.Pattern;

class _500_KeyboardRow {

    private static final Pattern ROWS = Pattern.compile("([qwertyuiop]+)|([asdfghjkl]+)|([zxcvbnm]+)");

    public static String[] findWords(String[] words) {
        if (words == null || words.length == 0) return new String[0];

        final ArrayList<String> res = new ArrayList<>();

        for (String str : words) {
            if (ROWS.matcher(str.toLowerCase()).matches()) {
                res.add(str);
            }
        }
        // Using an empty array as argument to Collection#toArray
        // to be efficient and concurrent-friendly since JDK 6
        return res.toArray(new String[0]);
    }
}
