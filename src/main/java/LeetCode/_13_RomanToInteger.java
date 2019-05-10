package LeetCode;

/*
https://leetcode.com/problems/roman-to-integer/
Easy. Math, String.

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, two is written as II in Roman numeral, just two one's added together.
Twelve is written as, XII, which is simply X + II.
The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right.
However, the numeral for four is not IIII. Instead, the number four is written as IV.
Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:


I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.
Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3

Example 2:

Input: "IV"
Output: 4

Example 3:

Input: "IX"
Output: 9

Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

import java.util.HashMap;
import java.util.Map;

class _13_RomanToInteger {

    private static final Map<Character, Integer> CHAR_VAL = new HashMap<>(7);

    static {
        CHAR_VAL.put('I', 1);
        CHAR_VAL.put('V', 5);
        CHAR_VAL.put('X', 10);
        CHAR_VAL.put('L', 50);
        CHAR_VAL.put('C', 100);
        CHAR_VAL.put('D', 500);
        CHAR_VAL.put('M', 1000);
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return -1;

        final char[] c = s.toCharArray();
        final int len = s.length();
        int res = CHAR_VAL.get(c[len - 1]);

        for (int i = len - 2; i >= 0; i--) {
            if (CHAR_VAL.get(c[i]) >= CHAR_VAL.get(c[i + 1])) {
                res += CHAR_VAL.get(c[i]);
            } else {
                res -= CHAR_VAL.get(c[i]);
            }
        }
        return res;
    }
}
