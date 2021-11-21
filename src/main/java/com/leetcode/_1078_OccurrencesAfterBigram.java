package com.leetcode;

/*
https://leetcode.com/problems/occurrences-after-bigram/
Easy. Hash Table.

Given words first and second, consider occurrences in some text of the form
"first second third", where second comes immediately after first,
and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
Example 2:

Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]

Note:

1 <= text.length <= 1000
text consists of space separated words, where each word consists of lowercase English letters.
1 <= first.length, second.length <= 10
first and second consist of lowercase English letters.
*/

import java.util.ArrayList;

class _1078_OccurrencesAfterBigram {

    public static String[] findOcurrences(String text, String first, String second) {
        if (text == null || first == null || second == null || text.length() == 0 || first.length() == 0 || second.length() == 0)
            return new String[0];

        final ArrayList<String> res = new ArrayList<>();
        final String[] words = text.split(" ");

        // Can be improved with Boyer-Moore like substring search
        for (int i = 2; i < words.length; i++) {
            if (first.equals(words[i - 2]) && second.equals(words[i - 1])) {
                res.add(words[i]);
            }
        }

        return res.toArray(new String[0]);
    }
}
