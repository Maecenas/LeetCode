package LeetCode;

/*
https://leetcode.com/problems/add-binary/
Easy. Math, String.

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"

Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

Constraints:

Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
*/

import java.math.BigInteger;

class _67_AddBinary {

    /**
     * O(max(m, n)), O(max(m, n))
     * Binary add with carry
     */
    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        else if (b == null || b.length() == 0) return a;

        final int m = a.length(), n = b.length();

        final StringBuilder sb = new StringBuilder();

        int carry = 0;
        int i = m - 1, j = n - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i--) == '1') carry++;
            if (j >= 0 && b.charAt(j--) == '1') carry++;

            if (carry % 2 == 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            carry /= 2;
        }
        if (carry == 1) sb.append('1');
        return sb.reverse().toString();
    }

    /**
     * O(m + n), O(max(m, n))
     * Bit Manipulation
     */
    public static String addBinary2(String a, String b) {
        if (a == null || a.length() == 0) return b;
        else if (b == null || b.length() == 0) return a;

        try {
            int x = Integer.valueOf(a, 2), y = Integer.valueOf(b, 2);
            while (y != 0) {
                // answer without carry
                x = x ^ y;
                // carry
                y = ((x ^ y) & y) << 1;
            }
            return Integer.toBinaryString(x);
        } catch (NumberFormatException e) {
            BigInteger x = new BigInteger(a, 2), y = new BigInteger(b, 2);
            while (!y.equals(BigInteger.ZERO)) {
                // answer without carry
                x = x.xor(y);
                // carry
                y = (x.xor(y).and(y)).shiftLeft(1);
            }
            return x.toString(2);
        }
    }
}
