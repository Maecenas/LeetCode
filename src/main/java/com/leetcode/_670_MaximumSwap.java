package com.leetcode;

/*
https://leetcode.com/problems/maximum-swap/
Medium. Array, Math.

Given a non-negative integer, you could swap two digits at most once to get
the maximum valued number. Return the maximum valued number you could get.

Example 1:

Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:

Input: 9973
Output: 9973
Explanation: No swap.

Note:

The given number is in the range [0, 10^8]
*/

/**
 * @see _31_NextPermutation
 */
class _670_MaximumSwap {

    private static final int R = 10;  // digits

    /**
     * O(n), O(n)
     */
    public static int maximumSwap(int num) {
        if (num <= 0) return num;

        // change an int to char[]
        final char[] digits = String.valueOf(num).toCharArray();
        final int[] last = new int[R];

        // last appearing index of number[i]
        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int d = R - 1; d > digits[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = tmp;
                    // change an array char[] to int
                    return Integer.valueOf(String.valueOf(digits));
                }
            }
        }
        return num;
    }

    /**
     * O(n), O(1)
     * <p>
     * Split the input digits into two parts: non-decreasing and the rest
     * The number if in reverse order if the rest is not found, return num
     * <p>
     * In the rest part, find the maximum digit (last appeared if appear more than once)
     * In the first part, find the first digit less than the digit found before
     * Swap the above two digits to get result
     */
    public static int maximumSwap2(int num) {
        if (num <= 0) return num;

        final int originalNum = num;

        // Pre-compute base to scan from left (Most Significant Digit)
        int base = 1;
        while (base <= num / R) {
            base *= R;
        }
        final int originalBase = base;

        // Split the input digits into two parts: non-decreasing and the rest
        // find the first digit greater than the more significant digit
        boolean hasFirstGreater = false;
        int prev = R;

        while (base != 0) {
            int digit = num / base;
            if (digit > prev) {
                hasFirstGreater = true;
                break;
            }
            prev = digit;
            num %= base;
            base /= 10;
        }

        // The input number if in reverse order if the rest is not found, return num
        if (!hasFirstGreater) return originalNum;

        int baseMax = base;
        int maxInLast = num / base;

        // In the rest of digits, find the maximum digit
        // (last appeared if appear more than once)
        while (base != 0) {
            int digit = num / base;
            if (base != baseMax && digit >= maxInLast) {
                baseMax = base;
                maxInLast = digit;
            }
            num %= base;
            base /= 10;
        }

        base = originalBase;
        num = originalNum;
        int firstLessThanMax = 0;

        // In the first part, find the first digit less than the digit found before
        while (base != 0) {
            firstLessThanMax = num / base;
            if (firstLessThanMax < maxInLast) {
                break;
            }
            num %= base;
            base /= 10;
        }

        // Swap the above two digits to get result
        return originalNum + (maxInLast - firstLessThanMax) * base + (firstLessThanMax - maxInLast) * baseMax;
    }
}
