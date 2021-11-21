package com.leetcode;

/*
https://leetcode.com/problems/range-sum-of-bst/
Easy. Tree, Recursion.

Given the root node of a binary search tree, return the sum of values
of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

class _938_RangeSumOfBst {

    private static int res;

    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;

        res = 0;
        dfs(root, L, R);
        return res;
    }

    private static void dfs(TreeNode node, int L, int R) {
        if (L <= node.val && node.val <= R) {
            res += node.val;
        }
        if (L < node.val && node.left != null) dfs(node.left, L, R);
        if (R > node.val && node.right != null) dfs(node.right, L, R);
    }
    public static int rangeSumBST2(TreeNode root, int L, int R) {
        if (root == null) return 0;

        int res = 0;
        final Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (L <= node.val && node.val <= R) {
                res += node.val;
            }
            if (L < node.val && node.left != null) {
                stack.push(node.left);
            }
            if (R > node.val && node.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }
}
