package com.leetcode;

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

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public static int minDepthBFS(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;

        final Deque<TreeNode> queue = new ArrayDeque<>();
        int depth = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i >= 0; i--) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {
                    return depth;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            depth++;
        }
        return depth;
    }
}
