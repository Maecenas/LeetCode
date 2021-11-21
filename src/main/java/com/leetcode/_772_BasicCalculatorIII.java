package com.leetcode;

/*
https://leetcode.com/problems/basic-calculator-iii/
Hard. String, Stack.

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces.

The expression string contains only non-negative integers, +, -, *, /
operators, open ( and closing parentheses ) and empty spaces.
The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate
results will be in the range of [-2147483648, 2147483647].

Some examples:
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

Note: Do not use the eval built-in library function.
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @see _224_BasicCalculator
 * @see _227_BasicCalculatorII
 * @see _770_BasicCalculatorIV
 */
class _772_BasicCalculatorIII {

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        // level-1 operator: o1 == 1 means '+'; o1 == -1 means '-';
        // level-2 operator: o2 == 1 means '*'; o2 == -1 means '/';
        int l1 = 0, o1 = 1, l2 = 1, o2 = 1, num = 0;

        final Deque<Integer> stack = new ArrayDeque<>();
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (Character.isDigit(ch)) {
                num = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(chars[i + 1])) {
                    num = num * 10 + (chars[++i] - '0');
                }
                l2 = (o2 == 1 ? l2 * num : l2 / num);
            }
            switch (ch) {
                case '(':
                    // preserve current calculation status
                    stack.addFirst(l1); stack.addFirst(o1);
                    stack.addFirst(l2); stack.addFirst(o2);

                    // reset state for next calculation
                    l1 = 0; o1 = 1;
                    l2 = 1; o2 = 1;
                    break;
                case ')':
                    // preserve the result of current calculation
                    num = l1 + o1 * l2;

                    // restore previous calculation status
                    o2 = stack.poll(); l2 = stack.poll();
                    o1 = stack.poll(); l1 = stack.poll();

                    // now resume previous calculation
                    l2 = (o2 == 1 ? l2 * num : l2 / num);
                    break;
                case '*':
                    o2 = 1;
                    break;
                case '/':
                    o2 = -1;
                    break;
                case '+':
                case '-':
                    l1 += o1 * l2;
                    o1 = (ch == '+' ? 1 : -1);
                    l2 = 1; o2 = 1;
                    break;
            }
        }
        return l1 + o1 * l2;
    }
}
