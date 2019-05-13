package LeetCode;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/
Medium. String, Backtracking.

Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

2 - abc
3 - def
4 - ghi
5 - jkl
6 - mno
7 - pqrs
8 - tuv
9 - wxyz

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:

Although the above answer is in lexicographical order,
your answer could be in any order you want.
*/

import java.util.ArrayList;
import java.util.List;

class _17_LetterCombinationsOfAPhoneNumber {

    private static final String[] PHONE = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            // assert digits.charAt(i) - '0' >= 0 && digits.charAt(i) - '0' <= 9;
            try {
                combine(res, PHONE[digits.charAt(i) - '0']);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException();
            }
        }
        return res;
    }

    private static void combine(final List<String> strings, final String digit) {
        for (int i = strings.size() - 1; i >= 0; i--) {
            String s = strings.get(i);
            strings.remove(i);
            for (int j = 0; j < digit.length(); j++) {
                strings.add(s + digit.charAt(j));
            }
        }
    }
}
