package com.leetcode;

/*
https://leetcode.com/problems/alien-dictionary/
Hard. Graph, Topological Sort.

There is a new alien language which uses the latin alphabet. However, the order
among letters are unknown to you. You receive a list of non-empty words from
the dictionary, where words are sorted lexicographically by the rules of this
new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"

Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"

Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see _953_VerifyingAnAlienDictionary
 */
class _269_AlienDictionary {

    private static final int R = 26;  // lowercase letters
    private static final List<Integer>[] reverseAdjList = new List[R];
    private static int size = 0;
    private static final Boolean[] seen = new Boolean[R];
    private static final StringBuilder sb = new StringBuilder();

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        Arrays.fill(reverseAdjList, null);
        size = 0;
        Arrays.fill(seen, null);
        sb.delete(0, sb.length());

        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (reverseAdjList[ch - 'a'] == null) {
                    reverseAdjList[ch - 'a'] = new ArrayList<>();
                    size++;
                }
            }
        }

        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList[word2.charAt(j) - 'a'].add(word1.charAt(j) - 'a');
                    break;
                }
            }
        }

        // Step 2: DFS to build up the output list.
        for (int i = 0; i < R; i++) {
            if (reverseAdjList[i] != null && !dfs(i)) return "";
        }

        if (sb.length() < size) return "";
        return sb.toString();
    }

    /**
     * Return true iff no cycles detected. Run the dfs from root c
     * @param c 0-indexed char of value 'a' + i
     * @return true iff no cycles detected.
     */
    private static boolean dfs(int c) {
        if (seen[c] != null) {
            return seen[c];  // If this node was grey (false), a cycle was detected.
        }
        seen[c] = false;
        for (int next: reverseAdjList[c]) {
            if (!dfs(next)) return false;
        }
        seen[c] = true;
        sb.append((char) ('a' + c));
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"z", "x", "z"};
        System.out.println("alienOrder(words) = " + (alienOrder(words).length() > 0 ? alienOrder(words) : "\"\""));
    }
}
