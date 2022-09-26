package com.leetcode;

/*
https://leetcode.com/problems/reverse-words-in-a-string/
Medium. Two Pointers, String.

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters.
The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces
between two words. The returned string should only have a single space
separating the words. Do not include any extra spaces.

Example 1:
 Input: s = "the sky is blue"
 Output: "blue is sky the"

Example 2:
 Input: s = "  hello world  "
 Output: "world hello"
 Explanation: Your reversed string should not contain leading or trailing
   spaces.

Example 3:
 Input: s = "a good   example"
 Output: "example good a"
 Explanation: You need to reduce multiple spaces between two words to a single
   space in the reversed string.

Constraints:
 1 <= s.length <= 10^4
 s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 There is at least one word in s.

Follow-up: If the string data type is mutable in your language,
can you solve it in-place with O(1) extra space?
*/

class _151_ReverseWordsInAString {

    public static String reverseWords(String s) {
        // convert string to string builder
        // and trim spaces at the same time
        final char[] chars = s.trim().toCharArray();
        final int len = trim(chars);

        // reverse the whole string
        reverse(chars, 0, len - 1);

        // reverse each word
        int i = 0, j = 0;
        while (i < len) {
            while (j < len && chars[j] != ' ') j++;
            reverse(chars, i, j - 1);
            i = j + 1;
            j = i;
        }

        return String.valueOf(chars, 0, len);
    }

    private static int trim(char[] chars) {
        int i = 0, j = 0;
        while (j < chars.length) {
            chars[i] = chars[j];
            i++;
            j++;
            if (chars[j - 1] == ' ') {
                while (j < chars.length && chars[j] == ' ') j++;
            }
        }
        return i;
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start++, end--);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
