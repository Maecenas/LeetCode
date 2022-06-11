package com.leetcode;

/*
https://leetcode.com/problems/add-strings/
Easy. Math, String, Simulation.

Given two non-negative integers, num1 and num2 represented as string,
return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling
large integers (such as BigInteger).
You must also not convert the inputs to integers directly.

Example 1:
 Input: num1 = "11", num2 = "123"
 Output: "134"

Example 2:
 Input: num1 = "456", num2 = "77"
 Output: "533"

Example 3:
 Input: num1 = "0", num2 = "0"
 Output: "0"

Constraints:
 1 <= num1.length, num2.length <= 10^4
 num1 and num2 consist of only digits.
 num1 and num2 don't have any leading zeros except for the zero itself.
*/

class _415_AddStrings {

    public static String addStrings(String num1, String num2) {
        if (num1 == null && num2 == null) return "";
        if (num1 == null || num2 == null) return num1 == null ? num2 : num1;

        final char[] chars1 = num1.toCharArray();
        final char[] chars2 = num2.toCharArray();

        final StringBuilder sb = new StringBuilder();
        int carry = 0, val;
        int i = chars1.length - 1;
        int j = chars2.length - 1;
        while (i >= 0 || j >= 0) {
            val = carry
                    + (i >= 0 ? (chars1[i] - '0') : 0)
                    + (j >= 0 ? (chars2[j] - '0') : 0);
            carry = val / 10;
            val = val % 10;
            sb.append(val);
            i--;
            j--;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
