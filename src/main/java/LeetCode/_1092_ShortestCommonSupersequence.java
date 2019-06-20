package LeetCode;

/*
https://leetcode.com/problems/shortest-common-supersequence/
Hard.

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T
(possibly 0, and the characters are chosen anywhere from T) results in the string S.)

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Note:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/

class _1092_ShortestCommonSupersequence {

    /**
     * O(m * n), O(m * n)
     */
    public static String shortestCommonSupersequence(String str1, String str2) {
        if (str1 == null || str2 == null) return "";
        else if (str1.length() == 0) return str2;
        else if (str2.length() == 0) return str1;

        // find LCS (Longest Common Subsequence)
        // dp[i + 1][j + 1] = lcs(A[:i], B[:j])
        final int m = str1.length(), n = str2.length();
        final int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // reversely build the result
        final StringBuilder sb = new StringBuilder();
        int i = m - 1, j = n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0 ^ j < 0) {
                // only one string reaches left end
                // remaining chars in the other string
                char c = i < 0 ? str2.charAt(j--) : str1.charAt(i--);
                sb.append(c);
            } else if (str1.charAt(i) == str2.charAt(j)) {
                // common char in LCS
                // append the char of either s1 or s2
                sb.append(str1.charAt(i));
                i--;
                j--;
            } else {
                // the char is not in LCS
                // the char corresponding to larger dp value
                char c = dp[i][j + 1] > dp[i + 1][j] ? str1.charAt(i--) : str2.charAt(j--);
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }
}
