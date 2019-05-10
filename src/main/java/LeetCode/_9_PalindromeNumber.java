package LeetCode;

/*
https://leetcode.com/problems/palindrome-number
Easy. Math.

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true

Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:

Could you solve it without converting the integer to a string?
*/

class _9_PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        final String s = String.valueOf(x);
        final char[] c = s.toCharArray();
        int len = c.length;
        for (int i = 0; i < len / 2; i++) {
            if (c[i] != c[len - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (x == rev || x == rev / 10);
    }
}
