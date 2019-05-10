package LeetCode;

/*
https://leetcode.com/problems/valid-parentheses/
Easy. String, Stack.

Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false

Example 4:

Input: "([)]"
Output: false

Example 5:

Input: "{[]}"
Output: true
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class _20_ValidParentheses {

    private static final Map<Character, Character> PAIR = new HashMap<>(3);

    static {
        PAIR.put(')', '(');
        PAIR.put('}', '{');
        PAIR.put(']', '[');
    }

    public static boolean isValid(String s) {
        if (s == null) return false;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (PAIR.containsKey(c)) {
                if (stack.empty() || stack.pop() != PAIR.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
