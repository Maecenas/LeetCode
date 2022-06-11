package com.leetcode;

/*
https://leetcode.com/problems/sum-of-two-integers/
Medium. Math, Bit Manipulation.

Given two integers a and b, return the sum of the two integers
without using the operators + and -.

Example 1:
 Input: a = 1, b = 2
 Output: 3

Example 2:
 Input: a = 2, b = 3
 Output: 5

Constraints:
 -1000 <= a, b <= 1000
*/

class _371_SumOfTwoIntegers {

    public static int getSum(int a, int b) {
        while (b != 0) {
            int tmp = a ^ b;
            int carry = (a & b) << 1;
            a = tmp;
            b = carry;
        }
        return a;
    }
}
