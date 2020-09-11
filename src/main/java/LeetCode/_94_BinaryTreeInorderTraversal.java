package LeetCode;

/*
https://leetcode.com/problems/binary-tree-inorder-traversal/
Medium. Hash Table, Stack, Tree.

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

import LeetCode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @see _144_BinaryTreePreorderTraversal
 * @see _145_BinaryTreePostorderTraversal
 */
class _94_BinaryTreeInorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode node) {
        final List<Integer> list = new ArrayList<>();
        if (node == null) return list;

        list.addAll(inorderTraversal(node.left));
        list.add(node.val);
        list.addAll(inorderTraversal(node.right));
        return list;
    }

    public static List<Integer> inorderTraversal2(TreeNode node) {
        final List<Integer> list = new ArrayList<>();
        if (node == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                // Add after all left children
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }
}
