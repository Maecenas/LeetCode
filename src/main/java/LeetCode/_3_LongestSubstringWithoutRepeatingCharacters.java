package LeetCode;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters
Medium. Hash Table, Two Pointers, String.

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

import java.util.Arrays;

class _3_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() <= 1) return s.length();

        char[] chars = s.toCharArray();
        int[] p = new int[256];
        Arrays.fill(p, -1);

        int res = 0, left = -1;
        for(int i = 0; i < s.length(); i++) {
            left = Math.max(left, p[chars[i]]);
            p[chars[i]] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }
}
