package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-maximum-path-sum/
Hard. Tree, Depth-first Search.

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from
some starting node to any node in the tree along the parent-child
connections. The path must contain at least one node and
does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/

import com.leetcode.utils.TreeNode;

/**
 * @see _112_PathSum
 * @see _129_SumRootToLeafNumbers
 */
class _124_BinaryTreeMaximumPathSum {

    private int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        // new path sum where node is the highest node
        maxValue = Math.max(maxValue, left + right + node.val);
        // max sum of the half way path
        return Math.max(left, right) + node.val;
    }
}
