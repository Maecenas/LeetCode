package com.leetcode;

/*
https://leetcode.com/problems/basic-calculator-ii/
Medium. String.

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, /
operators and empty spaces. The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7

Example 2:

Input: " 3/2 "
Output: 1

Example 3:

Input: " 3+5 / 2 "
Output: 5

Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @see _224_BasicCalculator
 * @see _772_BasicCalculatorIII
 * @see _770_BasicCalculatorIV
 */
class _227_BasicCalculatorII {

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        final Deque<Integer> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int res = 0, num = 0;
        char operator = '+';
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            // ch is operator or reach the end
            if (i == chars.length - 1 || "+-*/".indexOf(ch) >= 0) {
                // subtract top for multiply/ divide
                if ("*/".indexOf(operator) >= 0) {
                    res -= stack.peek();
                }
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        // only non-negative int input
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                operator = ch;
                res += stack.peek();
            } // else whitespace
        }
        return res;
    }

    public static int calculate2(String s) {
        if (s == null || s.length() == 0) return 0;

        final Deque<Integer> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int res = 0, num = 0;
        char operator = '+';
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            // ch is operator or reach the end
            if ("+-*/".indexOf(ch) >= 0) {
                // subtract top for multiply/ divide
                if ("*/".indexOf(operator) >= 0) {
                    res -= stack.peek();
                }
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                operator = ch;
                res += stack.peek();
            } // else whitespace
        }
        // trailing number
        switch (operator) {
            case '+':
                res += num;
                break;
            case '-':
                res -= num;
                break;
            case '*':
                res += stack.peek() * (num - 1);
                break;
            case '/':
                res += stack.peek() / num - stack.peek();
                break;
        }
        return res;
    }
}
