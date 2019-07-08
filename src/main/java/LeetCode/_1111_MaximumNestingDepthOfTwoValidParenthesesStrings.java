package LeetCode;

/*
https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
Medium.

A string is a valid parentheses string (denoted VPS) if and only if
it consists of "(" and ")" characters only, and:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.

We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.

For example, "", "()()", and "()(()())" are VPS's (with nesting depths
0, 1, and 2), and ")(" and "(()" are not VPS's.

Given a VPS seq, split it into two disjoint subsequences A and B,
such that A and B are VPS's (and A.length + B.length = seq.length).

Now choose any such A and B such that max(depth(A), depth(B))
is the minimum possible value.

Return an answer array (of length seq.length) that encodes such a choice
of A and B: answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.
Note that even though multiple answers may exist, you may return any of them.

Example 1:

Input: seq = "(()())"
Output: [0,1,1,1,1,0]

Example 2:

Input: seq = "()(())()"
Output: [0,0,0,1,1,0,1,1]

Constraints:

1 <= seq.size <= 10000
*/

class _1111_MaximumNestingDepthOfTwoValidParenthesesStrings {

    /**
     * O(n), O(n)
     * Alternatively distribute parentheses
     */
    public static int[] maxDepthAfterSplit(String seq) {
        if (seq == null || seq.length() == 0) return new int[0];

        final int n = seq.length();
        final int[] res = new int[n];
        final char[] chars = seq.toCharArray();

        // try to keep total points of two groups even by
        // alternatively distributing parentheses
        for (int i = 0; i < n; i++) {
            // count '(' as 1 point, ')' as -1 point
            res[i] = (chars[i] == '(')
                    ? i & 0x1
                    : (i & 0x1) ^ 0x1;
        }
        return res;
    }

    /**
     * O(n), O(n)
     * Keep two group even
     */
    public static int[] maxDepthAfterSplit2(String seq) {
        if (seq == null || seq.length() == 0) return new int[0];

        final int n = seq.length();
        final int[] res = new int[n];
        final char[] chars = seq.toCharArray();
        int A = 0, B = 0;

        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                if (A < B) {
                    A++;
                } else {
                    B++;
                    res[i] = 1;
                }
            } else {
                // chars[i] == ')'
                if (A > B) {
                    A--;
                } else {
                    B--;
                    res[i] = 1;
                }
            }
        }
        return res;
    }

    /**
     * O(n), O(n)
     * Split by half
     */
    public static int[] maxDepthAfterSplit3(String seq) {
        if (seq == null || seq.length() == 0) return new int[0];

        final int n = seq.length();
        int depth = 0, curr = 0;

        for (int i = 0; i < n; i++) {
            curr += (seq.charAt(i) == '(') ? 1 : -1;
            depth = Math.max(depth, curr);
        }

        final int[] res = new int[n];
        final int halfLen = depth >> 1;

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                curr++;
                if (curr > halfLen) {
                    res[i] = 1;
                }
            } else {
                if (curr > halfLen) {
                    res[i] = 1;
                }
                curr--;
            }
        }
        return res;
    }
}
