package LeetCode;

/*
https://leetcode.com/problems/basic-calculator/
Hard. Math, Stack.

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces.

Example 1:

Input: "1 + 1"
Output: 2

Example 2:

Input: " 2-1 + 2 "
Output: 3

Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @see _227_BasicCalculatorII
 * @see _772_BasicCalculatorIII
 * @see _770_BasicCalculatorIV
 */
class _224_BasicCalculator {

    /**
     * O(n), O(n)
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        final Deque<Integer> stack = new ArrayDeque<>();
        final char[] chars = s.toCharArray();
        int res = 0, num = 0, sign = 1;

        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                num = 10 * num + (ch - '0');
            }
            switch (ch) {
                case '+':
                    res += sign * num;
                    sign = 1;
                    num = 0;
                    break;
                case '-':
                    res += sign * num;
                    sign = -1;
                    num = 0;
                    break;
                case '(':
                    stack.push(res);
                    stack.push(sign);
                    sign = 1;
                    res = 0;
                    break;
                case ')':
                    res += sign * num;
                    res *= stack.pop();
                    res += stack.pop();
                    num = 0;
                    break;
            } // else whitespace
        }
        return res + (sign * num);
    }
}
