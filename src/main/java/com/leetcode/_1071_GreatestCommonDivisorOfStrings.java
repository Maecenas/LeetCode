package com.leetcode;

/*
https://leetcode.com/problems/greatest-common-divisor-of-strings/
Easy. String.

For strings S and T, we say "T divides S" if and only if S = T + ... + T
(T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""

Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.
*/

class _1071_GreatestCommonDivisorOfStrings {

    public static String gcdOfStrings(String str1, String str2) {
        if (str1 == null || str2 == null) return "";

        final String res = gcd(str1, str2);
        return res != null ? res : "";
    }

    private static String gcd(String str1, String str2) {
        if (str1 == null || str2 == null) return null;

        if (str1.length() < str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
        }
        if (str2.length() == 0) return str1;

        int index = str1.indexOf(str2);

        return gcd(str2,
                index != 0
                        ? null
                        : str1.substring(index + str2.length())
        );
    }
}
