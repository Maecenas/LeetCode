package com.leetcode;

/*
https://leetcode.com/problems/strobogrammatic-number/
Easy. Hash Table, Two Pointers, String.

Given a string num which represents an integer,
return true if num is a strobogrammatic number.

A strobogrammatic number is a number that looks the same
when rotated 180 degrees (looked at upside down).

Example 1:
 Input: num = "69"
 Output: true

Example 2:
 Input: num = "88"
 Output: true

Example 3:
 Input: num = "962"
 Output: false

Constraints:
 1 <= num.length <= 50
 num consists of only digits.
 num does not contain any leading zeros except for zero itself.
*/

import java.util.Map;

class _246_StrobogrammaticNumber {

    private static final Map<Character, Character> rotated = Map.of(
            '0', '0',
            '1', '1',
            '6', '9',
            '8', '8',
            '9', '6'
    );

    public static boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0 || num.length() > 50) return false;

        final char[] digits = num.toCharArray();
        for (int i = 0; i < (digits.length + 1) >> 1; i++) {
            char left = digits[i];
            char right = digits[digits.length - 1 - i];
            if (!rotated.containsKey(left) || rotated.get(left) != right) return false;
        }
        return true;
    }
}
