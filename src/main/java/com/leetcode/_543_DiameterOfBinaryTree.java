package com.leetcode;

/*
https://leetcode.com/problems/diameter-of-binary-tree/
Easy. Tree, Depth-First Search, Binary Tree.

Given the root of a binary tree, return the length of the diameter
of the tree.

The diameter of a binary tree is the length of the longest path between any
two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges
between them.

Example 1:
 Input: root = [1,2,3,4,5]
          1
         / \
        2   3
       / \
      4   5

 Output: 3
 Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
 Input: root = [1,2]
 Output: 1

Constraints:
 The number of nodes in the tree is in the range [1, 10^4].
 -100 <= Node.val <= 100
*/

import com.leetcode.utils.TreeNode;

class _543_DiameterOfBinaryTree {

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 0;

        final int[] res = dfs(root);
        return res[1];
    }

    /**
     * Returns (maxDepth, maxDiameter) of paths across the tree
     * <p>
     * Math: maxDepth(root) = 1 + max(maxDepth(left), maxDepth(right))
     *       maxDiameter(root) = max(maxDiameter(left),
     *                               maxDiameter(right),
     *                               maxDepth(left) + maxDepth(right))
     */
    private static int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        else if (root.left == null && root.right == null) return new int[]{1, 0};

        final int[] left = dfs(root.left);
        final int[] right = dfs(root.right);

        return new int[]{
                1 + Math.max(left[0], right[0]),
                Math.max(Math.max(
                        left[1],
                        right[1]),
                        left[0] + right[0]
                )
        };
    }

    int maxDiameter = 0;

    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 0;

        maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, leftMax + rightMax);
        return 1 + Math.max(leftMax, rightMax);
    }
}
