package com.leetcode;

/*
https://leetcode.com/problems/parsing-a-boolean-expression/
Hard. String.

Return the result of evaluating a given boolean expression,
represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...

Example 1:

Input: expression = "!(f)"
Output: true

Example 2:

Input: expression = "|(f,t)"
Output: true

Example 3:

Input: expression = "&(t,f)"
Output: false

Example 4:

Input: expression = "|(&(t,f,t),!(t))"
Output: false

Constraints:

1 <= expression.length <= 20000
expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
expression is a valid expression representing a boolean, as given in the description.

Hint 1:
Write a function "parse" which calls helper functions "parse_or", "parse_and", "parse_not".
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

class _1106_ParsingABooleanExpression {

    public static boolean parseBoolExpr(String expression) {
        if (expression == null || expression.length() == 0) return false;

        final HashSet<Character> set = new HashSet<>();
        final Deque<Character> stack = new ArrayDeque<>();

        for (char c : expression.toCharArray()) {
            if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    set.add(stack.pop());
                }
                // pop out '('
                stack.pop();
                // get operator for current expression
                char operator = stack.pop();
                if (operator == '&') {
                    // if there is any 'f', & expression results to 'f'
                    stack.push(set.contains('f') ? 'f' : 't');
                } else if (operator == '|') {
                    // if there is any 't', | expression results to 't'
                    stack.push(set.contains('t') ? 't' : 'f');
                } else {
                    // operator == '!'
                    // Logical NOT flips the expression
                    stack.push(set.contains('t') ? 'f' : 't');
                }
                set.clear();
            } else if (c != ',') {
                stack.push(c);
            }
        }
        if (stack.isEmpty()) return false;
        return stack.pop() == 't';
    }
}
