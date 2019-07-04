package LeetCode;

/*
https://leetcode.com/problems/minimum-depth-of-binary-tree/
Easy. Tree, Depth-first Search, Breath-first Search.

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its minimum depth = 2.
*/

import LeetCode.utils.TreeNode;

/**
 * @see _102_BinaryTreeLevelOrderTraversal
 * @see _104_MaximumDepthOfBinaryTree
 */
class _111_MinimumDepthOfBinaryTree {

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
