package LeetCode;

/*
https://leetcode.com/problems/reverse-integer
Easy. Math.

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321

Example 2:

Input: -123
Output: -321

Example 3:

Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers
within the 32-bit signed integer range: [−231, 231 − 1].
For the purpose of this problem, assume that your function returns 0
when the reversed integer overflows.
*/

class _7_ReverseInteger {

    public static int reverse(int x) {
        if (x == 0) return x;

        int rev = 0, pop;
        while (x != 0) {
            pop = x % 10;
            x /= 10;
            // Integer.MAX_VALUE's Least Significant Digit (LSD) is 7
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // Integer.MIN_VALUE's Least Significant Digit (LSD) is -8
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
