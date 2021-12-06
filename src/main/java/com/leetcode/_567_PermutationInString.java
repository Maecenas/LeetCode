package com.leetcode;

/*
https://leetcode.com/problems/permutation-in-string/
Medium. Hash Table, Two Pointers, String, Sliding Window.

Given two strings s1 and s2, return true if s2 contains a permutation of s1,
or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
 Input: s1 = "ab", s2 = "eidbaooo"
 Output: true
 Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
 Input: s1 = "ab", s2 = "eidboaoo"
 Output: false

Constraints:
 1 <= s1.length, s2.length <= 10^4
 s1 and s2 consist of lowercase English letters.
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @see _3_LongestSubstringWithoutRepeatingCharacters
 * @see _76_MinimumWindowSubstring
 * @see _438_FindAllAnagramsInAString
 */
class _567_PermutationInString {

    public static boolean checkInclusion(String t, String s) {
        if (t == null || t.length() == 0 || t.length() > 1e4
                || s == null || s.length() == 0 || s.length() > 1e4) return false;

        final Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        int left = 0, right = 0;
        int valid = 0;
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
            while (right - left == t.length()) {
                if (valid == need.size()) {
                    return true;
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
        return false;
    }
}
