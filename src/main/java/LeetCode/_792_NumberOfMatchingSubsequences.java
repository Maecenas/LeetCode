package LeetCode;

/*
https://leetcode.com/problems/number-of-matching-subsequences/
Medium. Array.

Given string S and a dictionary of words words,
find the number of words[i] that is a subsequence of S.

Example:
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words
that are a subsequence of S: "a", "acd", "ace".

Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
*/

import java.util.ArrayDeque;
import java.util.Deque;

class _792_NumberOfMatchingSubsequences {

    private static final int R = 26;  // lowercase letters

    public static int numMatchingSubseq(String S, String[] words) {
        if (S == null || words == null || S.length() == 0 || words.length == 0) return 0;

        final Deque<String>[] map = new Deque[R];
        final char[] text = S.toCharArray();

        for (char c : text) {
            if (map[c - 'a'] == null) {
                map[c - 'a'] = new ArrayDeque<>();
            }
        }

        for (String word : words) {
            char c = word.charAt(0);
            if (map[c - 'a'] != null) {
                map[c - 'a'].offer(word);
            }
        }

        int res = 0;
        for (char c : text) {
            Deque<String> q = map[c - 'a'];
            for (int k = q.size() - 1; k >= 0; k--) {
                String str = q.poll();
                if (str == null) continue;
                if (str.length() == 1) {
                    res++;
                } else if (map[str.charAt(1) - 'a'] != null) {
                    map[str.charAt(1) - 'a'].offer(str.substring(1));
                }
            }
        }
        return res;
    }
}
