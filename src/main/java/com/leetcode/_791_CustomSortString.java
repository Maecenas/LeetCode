package com.leetcode;

/*
https://leetcode.com/problems/custom-sort-string/
Medium. Hash Table, String, Sorting.

You are given two strings order and s.
All the words of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was
sorted. More specifically, if a character x occurs before a character y in order,
then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

Example 1:
 Input: order = "cba", s = "abcd"
 Output: "cbad"
 Explanation:
  "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c",
  "b", and "a".
  Since "d" does not appear in order, it can be at any position in the returned string.
  "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
 Input: order = "cbafg", s = "abcd"
 Output: "cbad"

Constraints:
 1 <= order.length <= 26
 1 <= s.length <= 200
 order and s consist of lowercase English letters.
 All the characters of order are unique.
*/

import java.util.Arrays;
import java.util.Comparator;

class _791_CustomSortString {

    private static final int R = 26;  // lowercase letters

    public static String customSortString(String order, String s) {
        if (order == null || order.length() == 0 || order.length() > 26
            || s == null || s.length() == 0 || s.length() > 200) return "";

        final Character[] chars = new Character[s.length()];
        int i = 0;
        for (char ch : s.toCharArray()) {
            chars[i++] = ch;
        }

        //Arrays.sort(chars, Comparator.comparingInt(ch -> order.indexOf((int) ch)));
        final int[] sequence = new int[R];
        Arrays.fill(sequence, -1);
        for (i = 0; i < order.length(); i++) {
            sequence[order.charAt(i) - 'a'] = i;
        }
        Arrays.sort(chars, Comparator.comparingInt(ch -> sequence[ch - 'a']));

        final StringBuilder sb = new StringBuilder();
        for (char ch : chars) sb.append(ch);
        return sb.toString();
    }
}
