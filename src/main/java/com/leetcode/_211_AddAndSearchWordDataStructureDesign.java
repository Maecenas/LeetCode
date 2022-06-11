package com.leetcode;

/*
https://leetcode.com/problems/add-and-search-word-data-structure-design/
Medium. Backtracking, Design, Trie.

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)

search(word) can search a literal word or a regular expression string
containing only letters a-z or `.`.
A `.` means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.
*/

class _211_AddAndSearchWordDataStructureDesign {

    static class WordDictionary {

        private static final int R = 26;  // lowercase letters
        private static final int WILDCARD = '.' - 'a';

        private static class TrieNode {
            final TrieNode[] next = new TrieNode[R];
            boolean isWord = false;

            public TrieNode() {

            }
        }

        final TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            put(root, word, 0);
        }

        private TrieNode put(TrieNode x, String key, int d) {
            if (x == null) x = new TrieNode();
            if (d == key.length()) {
                x.isWord = true;
                return x;
            }
            int c = key.charAt(d) - 'a';
            x.next[c] = put(x.next[c], key, d + 1);
            return x;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return get(root, word, 0);
        }

        private boolean get(TrieNode x, String key, int d) {
            if (x == null) return false;
            if (d == key.length()) return x.isWord;
            int c = key.charAt(d) - 'a';
            if (c != WILDCARD) {
                return get(x.next[c], key, d + 1);
            } else {
                for (int i = 0; i < R; i++) {
                    if (get(x.next[i], key, d + 1)) return true;
                }
                return false;
            }
        }
    }
}
