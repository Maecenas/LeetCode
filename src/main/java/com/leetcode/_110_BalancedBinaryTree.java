package com.leetcode;

/*
https://leetcode.com/problems/balanced-binary-tree/
Easy. Tree, Depth-first Search.

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node
never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7

Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4

Return false.
*/

import com.leetcode.utils.TreeNode;

/**
 * @see _104_MaximumDepthOfBinaryTree
 */
class _110_BalancedBinaryTree {

    public static boolean isBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        return depth(root) != -1;
    }

    private static int depth(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = depth(node.left);
        if (leftDepth == -1) return -1;

        int rightDepth = depth(node.right);
        if (rightDepth == -1) return -1;

        if (Math.abs(leftDepth - rightDepth) > 1) return -1;

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
