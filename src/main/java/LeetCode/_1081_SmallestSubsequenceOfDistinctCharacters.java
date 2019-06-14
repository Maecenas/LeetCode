package LeetCode;

/*
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
Medium. String.

Return the lexicographically smallest subsequence of text that contains all the
distinct characters of text exactly once.

Example 1:

Input: "cdadabcc"
Output: "adbc"
Example 2:

Input: "abcd"
Output: "abcd"
Example 3:

Input: "ecbacba"
Output: "eacb"
Example 4:

Input: "leetcode"
Output: "letcod"

Note:

1 <= text.length <= 1000
text consists of lowercase English letters.
*/

import java.util.Stack;

class _1081_SmallestSubsequenceOfDistinctCharacters {

    private static final int R = 26;  // lowercase letters

    public static String smallestSubsequence(String text) {
        if (text == null || text.length() == 0) return "";

        final char[] chars = text.toCharArray();
        final Stack<Integer> stack = new Stack<>();
        final int[] last = new int[R];
        final boolean[] marked = new boolean[R];

        for (int i = 0; i < text.length(); i++) {
            last[chars[i] - 'a'] = i;
        }
        for (int i = 0; i < text.length(); i++) {
            int c = chars[i] - 'a';
            if (marked[c]) continue;
            while (!stack.isEmpty() && stack.peek() > c && i < last[stack.peek()]) {
                marked[stack.pop()] = false;
            }
            stack.push(c);
            marked[c] = true;
        }

        final StringBuilder res = new StringBuilder();
        for (int c : stack) {
            res.append((char) ('a' + c));
        }
        return res.toString();
    }
}
