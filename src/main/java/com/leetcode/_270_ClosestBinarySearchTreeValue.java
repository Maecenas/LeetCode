package com.leetcode;

/*
https://leetcode.com/problems/closest-binary-search-tree-value/
Easy. Binary Search Tree, Depth-First Search, Binary Search Tree, Binary Tree.

Given the root of a binary search tree and a target value, return the value
in the BST that is closest to the target.


Example 1:
 Input: root = [4,2,5,1,3], target = 3.714286
 Output: 4

Example 2:
 Input: root = [1], target = 4.428571
 Output: 1

Constraints:
 The number of nodes in the tree is in the range [1, 10^4].
 0 <= Node.val <= 10^9
 -10^9 <= target <= 10^9
*/

import com.leetcode.utils.TreeNode;

class _270_ClosestBinarySearchTreeValue {

    private static final int NOT_FOUND = (int) (2e9 + 1);

    public static int closestValue(TreeNode root, double target) {
        if (target < -1e9 || target > 1e9) return -1;

        int res = root.val;
        TreeNode node = root;
        while (node != null) {
            if (Math.abs(node.val - target) < Math.abs(res - target)) {
                res = node.val;
            }
            if (node.val < target) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return res;
    }

    int res = 0;

    public int closestValue2(TreeNode root, double target) {
        if (target < -1e9 || target > 1e9) return -1;

        res = root.val;
        traverse(root, target);
        return res;
    }

    private void traverse(TreeNode root, double target) {
        if (root == null) return;

        if (Math.abs(root.val - target) < Math.abs(res - target)) {
            res = root.val;
        }
        if (root.val < target) {
            traverse(root.right, target);
        } else {
            traverse(root.left, target);
        }
    }
}
