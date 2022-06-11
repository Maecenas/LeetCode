package com.leetcode;

/*
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
Easy. String, Stack.

You are given a string s consisting of lowercase English letters. A duplicate
removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.
It can be proven that the answer is unique.

Example 1:
 Input: s = "abbaca"
 Output: "ca"
 Explanation:
  For example, in "abbaca" we could remove "bb" since the letters are adjacent
  and equal, and this is the only possible move.  The result of this move is that
  the string is "aaca", of which only "aa" is possible, so the final string is "ca".

Example 2:
 Input: s = "azxxzy"
 Output: "ay"

Constraints:
 1 <= s.length <= 10^5
 s consists of lowercase English letters.
*/

class _1047_RemoveAllAdjacentDuplicatesInString {

    public static String removeDuplicates(String s) {
        if (s == null || s.length() == 0 || s.length() > 1e5) return "";

        final StringBuilder sb = new StringBuilder();

        final char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (sb.length() > 0 && ch == sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
