package com.leetcode;

/*
https://leetcode.com/problems/remove-duplicate-letters/
Medium. String, Stack, Greedy, Monotonic Stack.

Given a string s, remove duplicate letters so that every letter appears
once and only once. You must make sure your result is the smallest
in lexicographical order among all possible results.

Example 1:
 Input: s = "bcabc"
 Output: "abc"

Example 2:
 Input: s = "cbacdcbc"
 Output: "acdb"

Constraints:
 1 <= s.length <= 10^4
 s consists of lowercase English letters.

Note: This question is the same as 1081:
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @see _1081_SmallestSubsequenceOfDistinctCharacters
 */
class _316_RemoveDuplicateLetters {

    private static final int R = 26;  // lowercase letters

    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0 || s.length() > 1e4) return "";

        final char[] chars = s.toCharArray();
        int[] last = new int[R];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - 'a'] = i;
        }

        final Deque<Integer> stack = new ArrayDeque<>();
        final boolean[] marked = new boolean[R];

        for (int i = 0; i < chars.length; i++) {
            int ch = chars[i] - 'a';
            if (!marked[ch]) {
                while (!stack.isEmpty() && stack.peek() > ch && i < last[stack.peek()]) {
                    marked[stack.pop()] = false;
                }
                stack.push(ch);
                marked[ch] = true;
            }
        }

        final StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append((char) (stack.pop() + 'a'));
        }
        return res.reverse().toString();
    }
}
