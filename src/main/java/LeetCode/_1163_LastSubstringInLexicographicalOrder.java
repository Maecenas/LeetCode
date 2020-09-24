package LeetCode;

/*
https://leetcode.com/problems/last-substring-in-lexicographical-order/
Hard. String, Suffix Array.

Given a string s, return the last substring of s in lexicographical order.

Example 1:

Input: "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"].
The lexicographically maximum substring is "bab".

Example 2:

Input: "leetcode"
Output: "tcode"

Note:

1 <= s.length <= 4 * 10^5
s contains only lowercase English letters.
*/

import java.util.SortedSet;
import java.util.TreeSet;

class _1163_LastSubstringInLexicographicalOrder {

    /**
     * O(n), O(1)
     * Two Pointers
     *
     * @see <a href="https://en.wikipedia.org/wiki/Lexicographically_minimal_string_rotation">Duval's Lyndon Factorization Algorithm</a>
     */
    public static String lastSubstring(String s) {
        if (s == null || s.length() == 0 || s.length() > 4e5) return "";

        final char[] chars = s.toCharArray();
        int i = 0, j = 1, offset = 0, len = s.length();
        while (i + offset < len && j + offset < len) {
            char c = chars[i + offset], d = chars[j + offset];
            if (c == d) {
                offset++;
            } else {
                if (c < d) {
                    // chars in [i, ..., i + offset] <= charAt(i) == charAt(j)
                    i += offset + 1;
                } else {
                    // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
                    j += offset + 1;
                }
                // avoid duplicate start index
                if (i == j) {
                    j++;
                }
                // reset offset to 0.
                offset = 0;
            }
        }
        return s.substring(i);
    }

    /**
     * O(n), O(n)
     * Encoding string weight from the least significant character
     */
    public static String lastSubstring2(String s) {
        if (s == null || s.length() == 0 || s.length() > 4e5) return "";

        final char[] chars = s.toCharArray();
        //final BigDecimal R = BigDecimal.valueOf(26);  // lowercase letters
        //int lo = 0;
        //BigDecimal max = BigDecimal.ZERO, curr = BigDecimal.ZERO;
        //for (int i = s.length() - 1; i >= 0; i--) {
        //    curr = curr.divide(R, MathContext.DECIMAL128).add(BigDecimal.valueOf(chars[i] - 'a'));
        //    if (max.compareTo(curr) <= 0) {
        //        max = curr;
        //        lo = i;
        //    }
        //}
        //return s.substring(lo);

        // if we use 26 as radix, would not pass due to limited precision
        // use a TreeSet to create character table appeared in input
        // TreeSet.headSet(ch).size() would return the index of ch in the table
        final SortedSet<Character> letters = new TreeSet<>();
        for (char ch : chars) {
            letters.add(ch);
        }
        final int radix = letters.size();
        int lo = 0;
        double max = 0.0, curr = 0.0;
        for (int i = s.length() - 1; i >= 0; i--) {
            curr = letters.headSet(chars[i]).size() + curr / radix;
            System.out.println(s.substring(i) + ": " + curr);
            if (max <= curr) {
                max = curr;
                lo = i;
            }
        }
        return s.substring(lo);
    }
}
