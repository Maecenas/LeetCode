package com.leetcode;

/*
https://leetcode.com/problems/group-shifted-strings/
Medium. Hash Table, String.

Given a string, we can "shift" each of its letter to its successive letter,
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"

Given a list of non-empty strings which contains only lowercase alphabets,
group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class _249_GroupShiftedStrings {

    private static final int R = 26;  // lowercase letters

    public static List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) return new ArrayList<>();

        final Map<Integer, List<String>> map = new HashMap<>();
        for (String string : strings) {
            int hashCode = hashCode(string);
            map.putIfAbsent(hashCode, new ArrayList<>());
            map.get(hashCode).add(string);
        }

        final List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    private static int hashCode(String string) {
        // shifted hash code, with a constant offset from string[0]
        if (string == null || string.length() == 0) return 0;

        final int offset = string.charAt(0);
        int hash = 17;
        for (char ch : string.toCharArray()) {
            hash = 31 * hash + (ch - offset + R) % R;
        }
        return hash;
    }
}
