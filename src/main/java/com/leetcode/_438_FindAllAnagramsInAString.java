package com.leetcode;

/*
https://leetcode.com/problems/find-all-anagrams-in-a-string/
Easy. Hash Table, Sliding Window.

Given a string s and a non-empty string p,
find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only
and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see _3_LongestSubstringWithoutRepeatingCharacters
 * @see _76_MinimumWindowSubstring
 * @see _567_PermutationInString
 */
class _438_FindAllAnagramsInAString {

    private static final int R = 26;  // lowercase letters

    public static List<Integer> findAnagrams(String s, String p) {
        final ArrayList<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) return res;

        final int[] chars = new int[R];
        for (char c : p.toCharArray()) {
            chars[c - 'a']++;
        }

        int start = 0, end = 0, counter = p.length();

        while (end < s.length()) {
            char c = s.charAt(end);
            chars[c - 'a']--;
            if (chars[c - 'a'] >= 0) {
                counter--;
            }
            end++;

            // sliding window of size p.length()
            if (end > p.length()) {
                c = s.charAt(start);
                // NOTE the sequence here
                if (chars[c - 'a'] >= 0) {
                    counter++;
                }
                chars[c - 'a']++;
                start++;
            }

            // after sliding window is initialized
            if (counter == 0 && end >= p.length()) {
                res.add(start);
            }
        }
        return res;
    }

    public static List<Integer> findAnagrams2(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) return new ArrayList<>();

        final Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }

        int left = 0, right = 0;
        int valid = 0;
        final List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            // update window
            if (need.containsKey(ch)) {
                window.merge(ch, 1, Integer::sum);
                if ((int) window.get(ch) == need.get(ch)) {
                    valid++;
                }
            }

            while (right - left == t.length()) {
                if (valid == need.size()) {
                    res.add(left);
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
        return res;
    }
}
