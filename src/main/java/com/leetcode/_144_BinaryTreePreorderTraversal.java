package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-preorder-traversal/
Medium. Stack, Tree.

Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @see _94_BinaryTreeInorderTraversal
 * @see _145_BinaryTreePostorderTraversal
 */
class _144_BinaryTreePreorderTraversal {

    public static List<Integer> preorderTraversal(TreeNode node) {
        if (node == null) return new ArrayList<>();

        final ArrayList<Integer> res = new ArrayList<>();

        res.add(node.val);
        res.addAll(preorderTraversal(node.left));
        res.addAll(preorderTraversal(node.right));
        return res;
    }

    public static List<Integer> preorderTraversal2(TreeNode node) {
        if (node == null) return new ArrayList<>();

        final ArrayList<Integer> res = new ArrayList<>();
        final Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                res.add(node.val);  // Add before going to children
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return res;
    }
}
