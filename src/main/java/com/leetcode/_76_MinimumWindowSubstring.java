package com.leetcode;

/*
https://leetcode.com/problems/minimum-window-substring/
Hard. Hash Table, String, Sliding Window.

Given two strings s and t of lengths m and n respectively,
return the minimum window substring of s such that
every character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Example 1:
 Input: s = "ADOBECODEBANC", t = "ABC"
 Output: "BANC"
 Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
 Input: s = "a", t = "a"
 Output: "a"
 Explanation: The entire string s is the minimum window.

Example 3:
 Input: s = "a", t = "aa"
 Output: ""
 Explanation: Both 'a's from t must be included in the window.
   Since the largest window of s only has one 'a', return empty string.

Constraints:
 m == s.length
 n == t.length
 1 <= m, n <= 10^5
 s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @see _3_LongestSubstringWithoutRepeatingCharacters
 * @see _438_FindAllAnagramsInAString
 * @see _567_PermutationInString
 */
class _76_MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || s.length() > 1e5
                || t == null || t.length() == 0 || t.length() > 1e5) return "";

        final Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }

        // Sliding window is [left, right)
        int left = 0, right = 0;
        int valid = 0;

        int minLeft = 0, minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            if (need.containsKey(ch)) {
                window.merge(ch, 1, Integer::sum);
                if ((int) window.get(ch) == need.get(ch)) {
                    valid++;
                }
            }

            // left window needs shrink
            while (valid == need.size()) {
                // update result
                if (right - left < minLen) {
                    minLeft = left;
                    minLen = right - left;
                }

                char del = s.charAt(left);
                left++;
                if (need.containsKey(del)) {
                    if ((int) window.get(del) == need.get(del)) {
                        valid--;
                    }
                    window.merge(del, -1, Integer::sum);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) return "";
        return s.substring(minLeft, minLeft + minLen);
    }
}
