package com.leetcode;

/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/
Easy. Tree, Depth-first Search.

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its depth = 3.
*/

import com.leetcode.utils.TreeNode;

/**
 * @see _110_BalancedBinaryTree
 * @see _111_MinimumDepthOfBinaryTree
 */
class _104_MaximumDepthOfBinaryTree {

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return 1 + Math.max(
                root.left == null ? 0 : maxDepth(root.left),
                root.right == null ? 0 : maxDepth(root.right));
    }
}
