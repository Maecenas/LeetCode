package LeetCode;

/*
https://leetcode.com/problems/unique-binary-search-trees/
Medium. Dynamic Programming, Tree.

Given n, how many structurally unique BST's (binary search trees)
that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * @see _95_UniqueBinarySearchTreesII
 */
class _96_UniqueBinarySearchTrees {

    /**
     * O(n), O(n)
     * Dynamic Programming
     */
    public static int numTrees(int n) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;

        // dp[i] is the result of input n
        // can be improved with memorization
        final int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int root = 1; root <= i; root++) {
                // num(leftBST) * num(rightBST)
                dp[i] += dp[root - 1] * dp[i - root];
            }
        }
        return dp[n];
    }

    /**
     * O(n), O(1)
     * Math, Catalan Number
     */
    public static int numTrees2(int n) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;

        // C(2n, n) / (n + 1)
        long res = 1;
        int i;
        for (i = 1; i <= n; i++) {
            res = res * (i + n) / i;
        }
        return (int) (res / i);
    }
}
