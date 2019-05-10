package LeetCode;

/*
https://leetcode.com/problems/zigzag-conversion/
Medium. String.

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows
like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
*/

class _6_ZigZagConversion {

    public static String convert(String s, int numRows) {
        if (s == null || s.length() <= 2 || numRows <= 1) return s;

        int len = s.length(), step = 2 * (numRows - 1), curStep;
        final char[] c = s.toCharArray();
        final StringBuilder res = new StringBuilder(len);

        for (int i = 0; i < numRows; i++) {
            // variable initializer
            // curStep = (curStep = step - 2 * i) > 0 ? curStep : 2 * i;
            curStep = (step - 2 * i) > 0 ? step - 2 * i : 2 * i;
            for (int curIndex = i; curIndex < len; curIndex += curStep, curStep = (step > curStep) ? step - curStep : step) {
                res.append(c[curIndex]);
            }
        }
        return res.toString();
    }
}
