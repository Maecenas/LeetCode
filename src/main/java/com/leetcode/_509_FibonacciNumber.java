package com.leetcode;

/*
https://leetcode.com/problems/fibonacci-number/
Easy. Array, Math.

The Fibonacci numbers, commonly denoted F(n) form a sequence,
called the Fibonacci sequence, such that each number is the sum of
the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.

Given N, calculate F(N).

Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

Note:

0 ≤ N ≤ 30.
*/

/**
 * @see _873_LengthOfLongestFibonacciSubsequence
 */
class _509_FibonacciNumber {

    private static final double SQRT_5 = Math.sqrt(5);

    /**
     * Recursive calls, O(n^2), O(1)
     */
    public static int fib1(int n) {
        if (n <= 0) return 0;
        else if (n <= 2) return 1;
        else return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * Iterative calls and lazily evaluation, O(n), O(1)
     */
    public static int fib2(int n) {
        if (n <= 0) return 0;
        else if (n <= 2) return 1;

        int a = 0, b = 1, res = 1;

        for (int i = 3; i <= n; i++) {
            a = b;
            b = res;
            res = a + b;
        }
        return res;
    }

    /**
     * Matrix multiplication, O(logn), O(1)
     */
    public static int fib3(int n) {
        int[][] start = {
                {1, 1},
                {1, 0}
        };
        return matrixPow(start, n - 1)[0][0];
    }

    private static int[][] matrixPow(int[][] start, int n) {
        if ((n & 1) == 0) {
            int[][] temp = matrixPow(start, n >> 1);
            return matMul(temp, temp);
        } else if (n == 1) {
            return start;
        } else {
            return matMul(start, matrixPow(start, n - 1));
        }
    }

    private static int[][] matMul(int[][] x, int[][] y) {
        final int[][] res = new int[x.length][y.length];
        int sum;

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y[0].length; j++) {
                sum = 0;
                for (int k = 0; k < x[0].length; k++) {
                    sum += x[i][k] * y[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }

    /**
     * Math formula, O(1), O(1)
     */
    public static int fib4(int n) {
        return (int) ((1 / SQRT_5)
                * (Math.pow((1 + SQRT_5) / 2, n) - Math.pow((1 - SQRT_5) / 2, n)));
    }
}
