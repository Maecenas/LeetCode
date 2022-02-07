package com.leetcode;

/*
https://leetcode.com/problems/house-robber-iii/
Medium. Dynamic Programming, Tree, Depth-First Search, Binary Tree.

The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour,
the smart thief realized that all houses in this place form a binary tree.
It will automatically contact the police
if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money
the thief can rob without alerting the police.

Example 1:
 Input: root = [3,2,3,null,3,null,1]
 Output: 7
 Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
 Input: root = [3,4,5,1,3,null,1]
 Output: 9
 Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

Constraints:
 The number of nodes in the tree is in the range [1, 10^4].
 0 <= Node.val <= 10^4
*/

import com.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see _198_HouseRobber
 * @see _213_HouseRobberII
 */
class _337_HouseRobberIII {

    public static int rob(TreeNode root) {
        if (root == null) return 0;

        return rob(root, new HashMap<>());
    }

    private static int rob(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);

        int robCurr = root.val
                + (root.left == null ? 0 : rob(root.left.left, memo) + rob(root.left.right, memo))
                + (root.right == null ? 0 : rob(root.right.left, memo) + rob(root.right.right, memo));
        int robNextRow = rob(root.left, memo) + rob(root.right, memo);
        int res = Math.max(robCurr, robNextRow);
        memo.put(root, res);
        return res;
    }

    public static int rob2(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    private static int[] dp(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = dp(root.left);
        int[] right = dp(root.right);

        int robRoot = root.val + left[1] + right[1];
        int skipRoot = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{robRoot, skipRoot};
    }
}
