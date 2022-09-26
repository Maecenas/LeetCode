package com.leetcode;

/*
https://leetcode.com/problems/repeated-dna-sequences/
Medium. Hash Table, String, Bit Manipulation, Sliding Window, Rolling Hash, Hash Function.

The DNA sequence is composed of a series of nucleotides
abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.

When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-
long sequences (substrings) that occur more than once in a DNA molecule.
You may return the answer in any order.

Example 1:
 Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
 Input: s = "AAAAAAAAAAAAA"
 Output: ["AAAAAAAAAA"]

Constraints:
 1 <= s.length <= 10^5
 s[i] is either 'A', 'C', 'G', or 'T'.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class _187_RepeatedDnaSequences {

    private static final int R = 4;
    private static final int L = 10;
    private static final int RL = (int) Math.pow(R, L - 1);

    public static List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() == 0 || s.length() > 1e5) return new ArrayList<>();

        final int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            switch (s.charAt(i)) {
                case 'A' -> nums[i] = 0;
                case 'C' -> nums[i] = 1;
                case 'G' -> nums[i] = 2;
                case 'T' -> nums[i] = 3;
            }
        }
        final Set<Integer> seen = new HashSet<>();
        final Set<String> res = new HashSet<>();

        int hash = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            hash = R * hash + nums[right];
            right++;
            if (right - left == L) {
                if (seen.contains(hash)) {
                    res.add(s.substring(left, right));
                } else {
                    seen.add(hash);
                }
                hash = hash - RL * nums[left];
                left++;
            }
        }
        return new ArrayList<>(res);
    }
}
