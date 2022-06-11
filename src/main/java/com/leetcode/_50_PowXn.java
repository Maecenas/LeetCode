package com.leetcode;

/*
https://leetcode.com/problems/powx-n/
Medium. Math, Recursion.

Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2⁻² = 1/2² = 1/4 = 0.25

Constraints:
 -100.0 < x < 100.0
 -2^31 <= n <= 2^31 - 1
 -10^4 <= xⁿ <= 10^4
*/

class _50_PowXn {

    public static double myPow(double x, int n) {
        if (x == 0.0 || x == 1.0 || x <= -100.0 || x >= 100.0) return x;
        else if (x == -1.0) {
            return (n & 0b1) == 0b1 ? -1.0 : 1.0;
        }

        boolean tMin = false;
        if (n == 0) return 1.0;
        else if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                tMin = true;
                n++;
            }
            x = 1.0 / x;
            // potential integer overflow
            n = -n;
        }

        // x^n
        double res = 1.0, step = x;
        while (n != 0) {
            if ((n & 0b1) == 0b1) {
                res *= step;
            }
            step *= step;
            n >>>= 1;
        }
        if (tMin) res *= x;
        return res;
    }
}
